
# [A2] Island ca.mcmaster.se2aa4.island.team102.Explorer

- Authors:
  - [Mansoor Lunawadi](lunawadm@mcmaster.ca) 
  - [Hashim Bukhtiar](bukhtiah@mcmaster.ca)
  - [Nehad Shikh Trab](shikhtrn@mcmaster.ca)

## Product Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. 

- The `ca.mcmaster.se2aa4.island.team_XXX_.Explorer` class implements the command center, used to compete with the others. (XXX being the team identifier)
- The `Runner` class allows one to run the command center on a specific map.

### Strategy description

The exploration strategy is for now to stop exploring as soon as we start. We stay safe and fly back to base immediately.

## How to compile, run and deploy

### Compiling the project:

```
>>> mvn clean package
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.960 s
[INFO] Finished at: 2024-01-20T18:26:43-05:00
[INFO] ------------------------------------------------------------------------
>>> 
```

This creates one jar file in the `target` directory, named after the team identifier (i.e., team 00 uses `team00-1.0.jar`).

As the project is intended to run in the competition arena, this jar is not executable. 

### Run the project

The project is not intended to be started by the user, but instead to be part of the competition arena. However, one might one to execute their command center on local files for testing purposes.

To do so, we ask maven to execute the `Runner` class, using a map provided as parameter:

```
mosser@azrael a2-template % mvn exec:java -q -Dexec.args="./maps/map03.json"
```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.

=======
# Drone-Rescue
>>>>>>> 47ef169d25f7e228b571dca362ec1cc84b2608dd
