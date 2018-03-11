Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile:
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0="input.txt" -Darg1="output1.txt"

-----------------------------------------------------------------------
##To generate a javadoc from command line
ant -buildfile src/build.xml doc

-----------------------------------------------------------------------
"I have done this assignment completely on my own. I have not copied
 it, nor have I given my solution to anyone else. I understand that if
 I am involved in plagiarism or cheating I will have to sign an
 official form that I have cheated and that this form will be stored in
 my official university record. I also understand that I will receive a
 grade of 0 for the involved assignment for my first offense and that I
 will receive a grade of F for the course for any additional
 offense."
 [Date:03/11/2018]
------------------------------------------------------------------------

Data Structure used:
ArrayList used for holding the courses
Queue is used to implement the course wait list 

Queue implementation is done to maintain the constraints for the category.
Here My assumption in the Queue
1) Last Element in the Category (Say D in Long programming) will never assigned until it get any course before as a 
pre-requisite like(A,B,C). Once the Course B is assigned A is not allowed to assign in future. So the student should 
enroll either C or D to be graduated. I followed this approach through out my Queue. 
2) Electives won't enter this queue and it will directly assign to the course list. Since the student is allowed to study
as many semester as he wanted. It is granted to take as many electives as a student can.
3) At the end of the preference list processing, elements remaining in the Queue is tried once to assign the courses if 
it follows the constraints and change the states accordingly.

Example: 
1234:A B E F I O W J Q R P sem 4: Graduated - All Categories satisfied.

1234:A B E F I O W J Q R NotGraduated 
   In the category M-P, O arrives first and assign in the list because it's less than the max(P) of that category and the 
   student should take P to finish that category.

State Implementation:
I am having 4 states
NotGraduated - Initial and Final State
Graduated - Final State
Mandatory - Intermediate 
Elective States - Intermediate

Suppose if the student is not graduated after assigning all his preference list then he moved back to the initial stage 
NotGraduated after processing all the preference inputs. 

Input & Output File assumption:
I assume the input.txt and delete.txt available in firstname_lastname_assign_1/studentCoursesPlanner/
And the corresponding output filename given will be created in the above specified directory. 

Output format:
Graduated students display with courses allocation along with semesters they took and graduate status:
Non graduated students display with courses allocation with Non graduated status.
1234:A B E F I O W J Q R X Y P sem 5: Graduated

------------------------------------------------------------------------
Provide list of citations (urls, etc.)
ant to generate javadoc: https://stackoverflow.com/questions/1495982/how-to-generate-javadoc-with-ant-for-an-existing-project



