package com.jeremias.paddlechampion.service.impl;

import static java.util.Collections.reverse;

import com.jeremias.paddlechampion.dto.*;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.TeamTournament;
import com.jeremias.paddlechampion.entity.TournamentEntity;
import com.jeremias.paddlechampion.entity.UserEntity;
import com.jeremias.paddlechampion.enumeration.Inscription;
import com.jeremias.paddlechampion.enumeration.Status;
import com.jeremias.paddlechampion.mapper.MatchMap;
import com.jeremias.paddlechampion.mapper.TeamMap;
import com.jeremias.paddlechampion.mapper.TeamTournamentMap;
import com.jeremias.paddlechampion.mapper.TournamentMap;
import com.jeremias.paddlechampion.mapper.exception.MatchesException;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.entity.MatchEntity;
import com.jeremias.paddlechampion.repository.MatchRepository;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.repository.TeamTournamentRepository;
import com.jeremias.paddlechampion.repository.TournamentRepository;
import com.jeremias.paddlechampion.repository.UserRepository;
import com.jeremias.paddlechampion.service.ITeamTournamentService;
import com.jeremias.paddlechampion.service.ITournamentService;

import java.sql.Date;
import java.util.*;

import com.jeremias.paddlechampion.service.IUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TournamentServiceImpl implements ITournamentService {

    @Autowired
    private TeamTournamentRepository teamTournamentRepo;
    @Autowired
    private MatchRepository matchRepo;
    @Autowired
    private TournamentRepository tournamentRepo;
    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ITeamTournamentService teamTournamentService;

    @Autowired
    private IUtilService util;

    @Autowired
    TournamentMap tournamentMap;
    @Autowired
    MatchMap matchMap;

    @Autowired
    TeamMap teamMap;

    @Autowired
    TeamTournamentMap teamTournamentMap;



    @Override
    public TournamentDto createTournament(TournamentDto dto) {

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByEmail(userEmail);

        TournamentEntity entity = tournamentMap.tournamentDto2Entity(dto);
        entity.setUser(user);
        entity.setInscriptionStatus(Inscription.OPEN);

        return tournamentMap.tournamentEntity2Dto(tournamentRepo.save(entity));
    }

    @Override
    public void startTournament(Long id){

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        tournament.setInscriptionStatus(Inscription.CLOSED);
        createRoundRobin(id);

        tournamentRepo.save(tournament);

    }

    @Override
    public TournamentDto getTournament(Long tournamentId) {

        TournamentEntity tournament = tournamentRepo.findById(tournamentId).orElseThrow(
                () -> new ParamNotFound("Team doesn't exist"));

        return tournamentMap.tournamentEntity2Dto(tournament);

    }

    @Override
    public void addTeam(Long tournamentId, Long teamId) {

        teamTournamentService.save(tournamentId, teamId);
    }

    @Override
    public void deleteTeam(Long tournamentId, Long teamId) {

        teamTournamentService.delete(tournamentId, teamId);
    }

    @Override
    public PageDto<MatchBasicDto> listMatches(Pageable page, HttpServletRequest request, Long id){

        PageDto<MatchBasicDto> pageDto = new PageDto<>();
        Map<String,String> links = new HashMap<>();
        List<MatchBasicDto> listDto = new ArrayList<>();

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        if(tournament.getMatchEntities() == null){
            throw new MatchesException("This tournament has no matches created");
        }

        Page<MatchEntity> elements =  matchRepo.findAllByTournament(tournament,page);

        elements.getContent().forEach(element -> listDto.add(matchMap.matchEntity2BasicDto(element)));
        links.put("next",elements.hasNext()?util.makePaginationLink(request,page.getPageNumber()+1):"");
        links.put("previous",elements.hasPrevious()?util.makePaginationLink(request,page.getPageNumber()-1):"");

        pageDto.setContent(listDto);
        pageDto.setLinks(links);

        return pageDto;
    }


    @Override
    public List<MatchBasicDto> createRoundRobin(Long id) {

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        List<TeamEntity> teams = new ArrayList<>(getTeams(id));

        if (!tournament.getMatchEntities().isEmpty()) {

            return matchMap.matchEntityList2BasicDto(tournament.getMatchEntities());

        }

        if (teams.size() % 2 != 0) {
            TeamEntity free = teamRepo.findByName("FREE");
            teams.add(free);
        }

        int numRounds = (teams.size() - 1);
        int halfSize = (teams.size() / 2);

        List<TeamEntity> teamEntities = new ArrayList<>(teams);
        teamEntities.remove(0);

        List<MatchEntity> matchEntities = new ArrayList<>();

        int teamsSize = teamEntities.size();

        for (int round = 0; round < numRounds; round++) {

            int teamIdx = round % teamsSize;

            MatchEntity matchEntityA = new MatchEntity(teamEntities.get(teamIdx), teams.get(0),
                    Status.NOT_PLAYED, tournament);

            matchRepo.save(matchEntityA);
            matchEntities.add(matchEntityA);

            for (int idx = 1; idx < halfSize; idx++) {

                TeamEntity teamA = teamEntities.get((round + idx) % teamsSize);
                TeamEntity teamB = teamEntities.get((round + teamsSize - idx) % teamsSize);

                MatchEntity matchEntityB = new MatchEntity(teamA, teamB, Status.NOT_PLAYED, tournament);

                matchRepo.save(matchEntityB);
                matchEntities.add(matchEntityB);

            }

        }

        return matchMap.matchEntityList2BasicDto(matchEntities);
    }

    @Override
    public void delete(Long id) {
        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid")
        );
        tournamentRepo.delete(tournament);
    }

    @Override
    public TournamentDto update(Long id, TournamentDto dto) {

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid")
        );

        tournament.setName(dto.getName());
        tournament.setMaxTeams(dto.getMaxTeams());
        tournament.setMaxCategory(dto.getMaxCategory());
        tournament.setInscriptionStatus(dto.getInscriptionStatus());

        return dto;
    }

    @Override
    public List<TeamEntity> getTeams(Long id) {

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        List<TeamEntity> teams = new ArrayList<>();

        for (TeamTournament teamTournament : tournament.getTeamTournaments()) {

            teams.add(teamTournament.getTeam());

        }

        return teams;
    }

    public List<TeamDto> getTeamsDto(Long id) {

        return teamMap.teamEntityList2DtoList(this.getTeams(id));
    }

    public void updateTeamTournaments(Long id) {

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        List<MatchEntity> matches = new ArrayList<>();
        matches.addAll(tournament.getMatchEntities());
        List<TeamTournament> teamTournaments = new ArrayList<>();

        for (TeamTournament teamTournament : tournament.getTeamTournaments()) {

            teamTournament.setMatchesPlayed(0);
            teamTournament.setMatchesLost(0);
            teamTournament.setMatchesWon(0);
            teamTournament.setPoints(0);


        }

        for (MatchEntity match : matches) {

            if (match.getStatus().equals(Status.FINISHED)) {

                TeamEntity teamA = teamRepo.findByName(match.getTeamA());
                TeamEntity teamB = teamRepo.findByName(match.getTeamB());

                TeamTournament teamTournamentA = teamTournamentRepo.findByTournamentAndTeam(tournament,
                        teamA);
                TeamTournament teamTournamentB = teamTournamentRepo.findByTournamentAndTeam(tournament,
                        teamB);

                teamTournamentA.setMatchesPlayed((teamTournamentA.getMatchesPlayed() + 1));
                teamTournamentB.setMatchesPlayed((teamTournamentB.getMatchesPlayed() + 1));

                if (match.getWinner().equals(teamA.getName())) {
                    teamTournamentA.setMatchesWon((teamTournamentA.getMatchesWon() + 1));
                    teamTournamentB.setMatchesLost((teamTournamentB.getMatchesLost() + 1));

                    teamTournamentA.setPoints((teamTournamentA.getPoints() + 3));

                } else {
                    teamTournamentB.setMatchesWon((teamTournamentB.getMatchesWon() + 1));
                    teamTournamentA.setMatchesLost((teamTournamentA.getMatchesLost() + 1));

                    teamTournamentB.setPoints((teamTournamentB.getPoints() + 3));
                }

                teamTournamentRepo.save(teamTournamentA);
                teamTournamentRepo.save(teamTournamentB);

            }
        }
    }


    public List<TeamTournamentDto> getPositionsTable(Long id) {

        this.updateTeamTournaments(id);

        TournamentEntity tournament = tournamentRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("Tournament ID is invalid"));

        Comparator<TeamTournament> compareByPoints = Comparator.comparing(TeamTournament::getPoints);

        List<TeamTournament> teamTournaments = new ArrayList<>();

        teamTournaments.addAll(tournament.getTeamTournaments());
        teamTournaments.sort(compareByPoints);
        reverse(teamTournaments);

        List<TeamTournamentDto> dtos = teamTournamentMap.teamTournamentList2Dto(teamTournaments);

        int index = 1;
        for (TeamTournamentDto teamTournamentDto : dtos) {
            teamTournamentDto.setPosition(index);
            index++;
        }

        return dtos;
    }


}

