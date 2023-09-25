# Unit 4 Project: Playlist Service

### Background

Amazon Music Unlimited is a premium music subscription service featuring tens of millions of songs 
available to our millions of customers. We currently provide expert curated, premade playlists, but 
customers have often requested the ability to create and manage their own custom playlists.
This design document describes the Amazon Music Playlist Service, a new AWS service that will provide 
the custom playlist functionality to meet our customers’ needs. It is designed to interact with the 
Amazon Music client and will return a list of song metadata associated with the playlist that the 
Amazon Music client can use to fetch the song file when playing.

This initial iteration will provide the minimum lovable product (MLP) defined by our product team 
including creating, retrieving, and updating a playlist, as well as adding to and retrieving a saved 
playlist’s list of songs.


## Business Requirements

As a customer, I want to create a new, empty playlist with a given name and a list of tags.
As a customer, I want to retrieve my playlist with a given ID.
As a customer, I want to update my playlist name.
As a customer, I want to add a song to the end of my playlist.
As a customer, I want to add a song to the beginning of my playlist.
As a customer, I want to retrieve all songs in my playlist, in a provided order (in order, reverse order, shuffled).


## Project Mastery Tasks

### [Mastery Task 1: Finish what we started.](tasks/project-master-tasks/MasteryTask01.md)

### [Mastery Task 2: Submit to the process](tasks/MasteryTask02.md)

### [Mastery Task 3: Query, query on the wall, don’t load one, get them all!](tasks/MasteryTask03.md)

### [Mastery Task 4: Make a run(nable) for it](tasks/MasteryTask04.md)

&nbsp;
