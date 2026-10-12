# New York Yankees App

This app keeps track of stats for the New York Yankees.

## Screens



* **Home** - Displays a header with a picture of the Yankee logo. There is a text box for the user to enter a new stat and add it to a list of existing stats. There are two other buttons; one to see a list of all the stats and another to go to an about screen.
* **List** - A scrollable list of every stat. The user can remove each one and also has an option to remove all.
* **About** - A description of the app and its purpose



## Input rules

Input   	| What the app says

empty  		| Enter a stat name
too short	| Too short - at least 3 characters
too long	| Keep it to 30 characters or fewer
duplicate stat  | Stat is already on the list
only numbers    | A name can't be only numbers

## How to run

1. Clone this repo
2. Open the cloned folder in Android Studio
3. Wait for Gradle sync, then Run

## Known limitations

* The picture on the top of the home screen overlaps with the time and battery percentage on the phone.
* When you rotate the phone, the list resets to the default list and removes any added stats and adds back deleted ones

