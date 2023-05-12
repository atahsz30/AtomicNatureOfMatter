
# Atomic Nature of Matter

[The project of Princeton university](https://www.cs.princeton.edu/courses/archive/spr23/cos126/static/assignments/atomic/videos/atomic.webm)


This priject is aimed at finding Avogadro's number which is fundemental constant used in chemistry and phisics.
In this project, we obtain Avogadro's number using four classes that we have written in the Java programming environment.


## Tables of contents

 _ [ introduction ]

 _ [ usage ]

 _ [ results , eamples of classes ]

 _ [ contributing ]
 
 _ [ dependencies ]

 _ [ contact ]

 _ [ creators' names ]

## Introduction 

Avogadro's number is defined as the number of atoms in 12 grams of carbon-12, which is approximately 6.022 x 10^23.
 This constant is used to convert between the atomic scale and the macroscopic scale in chemistry and physics. 
 In this project, we will redo a version of Perrin's experiment.  we will measure and track the motion of an immersed particle undergoing Brownian motion.
 We supply video microscopy data of polystyrene spheres ("beads") suspended in water, undergoing Brownian motion.
 we write a program to analyze this data, determine how much each bead moves between observations, fit this data to Einstein's model, and estimate Avogadro's number.





## usage

 The classes defined within the project have various tasks that you need to know before running them. 
The classes and their tasks are as follows in order:

1. Blob.java :  Representation of a set of light pixels (connected or unconnected) defined as a complete set representing a polystyrene “blob”.
   Has a defined mass and center of mass coordinates in the x-y plane.

2. BeadFinder.java : Designates a blob of a minimum designated number of pixels ( min = 25 ) as a “bead”.
   Takes in a picture containing blobs and determines which blobs can be considered beads using a recursive depth-first search algorithm.
   This filters out all extraneous light in each frame of the video to track the actual polystyrene beads in question. This class receives the image, threshold value, and minimum pixel value from the command line.


3. BeadTracker.java : Tracks beads and their movement from one frame of a video to the next. Prints the distance change (radial displacement) of each bead after each frame change.
   This class takes an integer P, a double value tau, a double value delta, and a sequence of jpg filenames as command-line arguments, identifies the beads (using the specified values of P and tau) 
   in each jpg image (using BlobFinder), and prints out (one per line, formatted with 4 decimal places to the right of decimal point) the radial displacement that each bead moves from one frame to 
   the next (assuming it is no more than delta).



4. Avogadro.java :  Calculates a self diffusion constant which is then used to approximate the Boltzmann constant and Avogadro’s number from the radial displacements found from BeadTracker.java.
   "The Avogadro class receives the output of the BeadTracker class and estimates the Avogadro's number using the formulas written in the Boltzmann constant static method."




 How to run:

  Copy these lines of argv, so as to run the codes.

  Src/ BeadFinder
  java BeadFinder 25 180.0 run_1/frame00001.jpg


  Src/ BeadTracker
  java BeadTracker 25 180.0 25.0 run_1/*.jpg
  

  Src/ Avogadro
  BeadTracker 25 180.0 25.0 run_1/*.jpg | java Avogadro



## results and eamples of class outputs

1. Blob.java :

  31 (370.9355, 365.4194)


2. BeadFinder.java :

  % java BeadFinder 25 180.0 run_1/frame00001.jpg
  
  29 (214.7241,  82.8276)
  36 (223.6111, 116.6667)
  42 (260.2381, 234.8571)
  35 (266.0286, 315.7143)
  31 (286.5806, 355.4516)
  37 (299.0541, 399.1351)
  35 (310.5143, 214.6000)
  31 (370.9355, 365.4194)
  28 (393.5000, 144.2143)
  27 (431.2593, 380.4074)
  36 (477.8611,  49.3889)
  38 (521.7105, 445.8421)
  35 (588.5714, 402.1143)



  29 (214.7241,  82.8276)
  36 (223.6111, 116.6667)
  1 (254.0000, 223.0000)
  42 (260.2381, 234.8571)
  35 (266.0286, 315.7143)
  31 (286.5806, 355.4516)
  37 (299.0541, 399.1351)
  35 (310.5143, 214.6000)
  31 (370.9355, 365.4194)
  28 (393.5000, 144.2143)
  27 (431.2593, 380.4074)
  36 (477.8611,  49.3889)
  38 (521.7105, 445.8421)
  35 (588.5714, 402.1143)
  13 (638.1538, 155.0000)


3. BeadTracker.java : 

  % java BeadTracker 25 180.0 25.0 run_1/*.jpg
  7.1833
  4.7932
  2.1693
  5.5287
  5.4292
  4.3962
  ...


4. Avogadro.java : 

  % java BeadTracker 25 180.0 25.0 run_1/*.jpg | java Avogadro
    Boltzmann = 1.2535e-23
    Avogadro  = 6.6330e+23



## contributing 

Contributions to this project are welcome. If you have any suggestions or improvements, please feel free to submit a pull request or open an issue.


## dependencies

[ You can get help from these sites ] (https://introcs.cs.princeton.edu/java/stdlib/Picture.java.html)
[ You can see the introduction of the project which is presented by Princeton University from this site ] (https://introcs.cs.princeton.edu/java/assignments/atomic.html)






## creators :

   Zahra Noruzzadeh _ 140115355031

   Hannaneh Hazratian _ 14015361916

   Parniyan Modirshahla _ 14015361942

   Ata Hasanzadeh  _ 14015361915




