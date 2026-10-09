1. My first build took a couple of minutes, but the second one was much quicker and probably wasn't even a minute

2. The background of the app changed color on its own

3. Most of the files in res are confusing and I don't understand them yet


Week 5 Friday:
    I changed .fillMaxWidth in Image to .fillMaxSize and the entire sreen was filled with the image.

Week 6 Wednesday:
    1. I got five errors: 
        - Exception in NativeInputEventReceiver callbacks
        - Failed to dispatch motion event to Java
        - FATAL EXCEPTION: main
        - Attempting to externally change a non-organized container: Task{2a5850b #11 type=standard 
          A=10230:edu.lemoyne.campusapp}={handlePackageUpdate:false,} playercount=2 taskorg=android.
          window.ITaskOrganizer$Stub$Proxy@4f0eddf
        - [58] ItemStore: getItems RPC failed for item edu.lemoyne.campusapp
    2. Count changed, but the screen didn't because there wasn't a remember function.
    3. Remember updates the value on the screen with every click. If you didn't have a remember, 
       the count on the screen wouldn't update 

Week 6 Friday:
    Task 2: I added that the input can't only be numbers. My app tracks stats for a team with a description of each stat. If the input is 	    only numbers, then there can't be a description.

    Task 3: 
	What I typed | What the app did | Correct?
	nothing      | add button greyed out | yes
	only spaces  | add button greyed out | yes
	too long     | won't let me add more than 30 characters | yes
	too short    | gave an error for too short 		| yes
	Duplicate in different capitals | said that the input was already on the list | yes
	Own rule failing | gives an error that says only numbers | yes
	own rule passing | adds the input to the list		| yes
	One valid item   | adds the input to the list		| yes
	

Week 7 Wednesday:
	When I was on the list screen and rotated, I remained on the list screen, but my new items weren't there. This is because currentScreen is created using rememberSaveable and trails is created using remember. rememberSaveable preserves the state during transitions like screen rotations.

    