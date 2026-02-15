##### Scan Conversion Algorithms Visualizer ##### 
Step-by-Step Visualization of Scan Conversion Algorithms using Java Swing

##### Description ##### 
This project is a Java Swing-based graphical application that demonstrates:
Bresenham’s Line Drawing Algorithm
Midpoint Circle Drawing Algorithm
The application allows users to input coordinates and visually see how pixels are generated step-by-step using scan conversion techniques.
It is designed for educational purposes to help understand how computer graphics algorithms work internally.

##### Objectives
To implement Bresenham’s Line Algorithm
To implement Midpoint Circle Algorithm
To visualize pixel-by-pixel drawing
To provide step-by-step animation
To allow user input through GUI

##### Technologies Used ##### 
Java
Java Swing
AWT Graphics
Timer-based animation

 ##### Features ##### 
 User input for line coordinates (x1, y1, x2, y2)
 User input for circle center and radius
 Step-by-step animated drawing
 Clear button to reset canvas
 Works for all line slopes (all octants)
 Smooth animation using Swing Timer

##### Algorithms Implemented ##### 
1) Bresenham’s Line Algorithm
Efficient incremental algorithm
Uses integer arithmetic
Works for all slopes
Avoids floating-point operations
2️) Midpoint Circle Algorithm
Uses decision parameter
Exploits 8-way symmetry
Efficient pixel generation
Integer-based calculations

##### How to Run the Project ##### 
Step 1: Install JDK
Check installation:
javac -version
java -version
Step 2: Compile the Program
Open terminal or PowerShell in project folder and run:
javac DrawingUI.java
Step 3: Run the Program
java DrawingUI

##### How to Use ##### 
Drawing a Line
Enter x1, y1, x2, y2
Click "Draw Line"
Watch step-by-step animation
Drawing a Circle
Enter center (xc, yc)
Enter radius
Click "Draw Circle"
Watch pixel-by-pixel drawing
Clear Screen
Click "Clear" button.

##### Output ##### 
Pixels are displayed one-by-one
Each step shows how algorithm progresses
Demonstrates scan conversion visually

##### Learning Outcome ##### 
After completing this project, I understood:
How raster graphics work
How line and circle drawing algorithms operate
How decision parameters affect drawing
GUI programming using Java Swing
Animation using Timer class
