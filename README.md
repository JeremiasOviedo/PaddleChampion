# PaddleChampion

![PaddleChampion-Logo](https://jeremiasoviedo.github.io/images/logo-no-background.png)
Restful API used to create paddle tournaments.

# I am currently working on this project :wrench:


## So far so good..

### Technologies im using
<ul>
<li>JAVA 17</li>
<li>Spring Boot 2.7.4</li>
<li>Spring Security with JWT</li>
<li>JPA HIBERNATE</li>
<li>Postman for endpoint testing</li>
<li>MySQL Workbench for DB visualization</li>
</ul>

*These are some functionalities i finished, i'm currently working and progressing in additional features*

## Registration 

I implemented Spring Security with JWT, you need to create a user and login to get full access to the endpoints

![Register](https://i.imgur.com/ACjWEa6.png)

*Category : this is a number that classifies a player into a rank. The higher the number, the higher the rank*

### Password encriptation
Spring provide a password encrypter that encrypt and decrypt your password for more safety

![PasswordEncrypt](https://i.imgur.com/KkxnY2t.png)

## Login

When you login with to your account you will get a Json Web Token, this token will grant you full authorization to all the endpoints.

![Login](https://i.imgur.com/1qCMtWd.png)

## Creating a team

You can create your own teams! each team has a category that is the addition of both members categories. This acts like a filter for tournaments

![creating-a-team](https://i.imgur.com/OMaHL5t.png)

*When you create a team this automatically adds youn like a member, you can add a second member later.*

## Adding a second member to a team

Adding the second member will refresh the team category
![Adding-a-player](https://i.imgur.com/Th18Nr5.png)

## Creating your own tournament

You can create your own tounaments too! Choose a name to the tournament, you can add a max category to the teams and even a fixed number of teams that can participate
![Creating-a-tournament](https://i.imgur.com/odpauQS.png)

## Adding teams to the tournament

You can add teams to the tournament, in the future this will have an invitation feature or a join request. For the moment and for development reasons i add the teams
manually with their Ids.

![Adding-teams](https://i.imgur.com/nFCcbJq.png)

## List of the teams

For this example i added four different teams to the tournament. 

![Team-List](https://i.imgur.com/U25Gnct.png)

## Creating the fixture

When you have all your teams added you are ready to begin! I implemented a round robin algorithm that automatically creates the matches between all the teams!

![Creating-Fixture](https://i.imgur.com/5IU791t.png)

## Match Info

You can view more detail info of a match using the match controller

![Match-Info](https://i.imgur.com/FjpWGHf.png)

## Updating a match

When a match is finished you can load the result, this will automatically set the match status to finished and select the winner depending on the result.

![Updating-a-Match](https://i.imgur.com/9nRy3ez.png)

## Positions table

I created an algorithm that get all the matches in the tournament with the Status FINISHED and makes a positions table based on the results.

![Positions-Table](https://i.imgur.com/sBENh2n.png)



# TO DO--
So far this is what ive done with this API, im currently working on it and learning new things every day.
Some of the next features i need to add are:

<ul>
<li>Update the matches when finished :heavy_check_mark: DONE</li>
<li>Create an algorithm that automatically updates the positions table based on the results of the matches. :heavy_check_mark: DONE</li>
<li>When finished the round robin phase, begin the Knockout Phase with an algorithm that create the brackets based on the positons table.</li>
<li>Implement a reward system</li>
<li>Implement fictional cash fees to enter the tournaments and create a cash pool with all the inscriptions, this pool will be the reward for the winner</li>
<li>Profile customization with avatar image, same for the tournament</li>
<li>Add a location based filter that lists all the open tournaments on a certain KM radius</li>
</ul>

### Hope you liked my idea! if you have questions or wanna collaborate feel free to DM me!
