# A Simple Fixed-Point Calculator

The goal of this project is to make a simple and convenient [SpeedCrunch](https://bitbucket.org/heldercorreia/speedcrunch/src/master/)-style calculator for fixed-point arithmetic. The GUI should consist of a single window, which contains a text field at the bottom, a settings bar at the top and a list of previous expressions in the middle.

## The app should support the following features:
* The user can write a fixed-point arithmetic expression made of ASCII characters to a text field
* The result of the expression is displayed in different formats whenever the expression is valid
* When the user presses Enter, the text field is cleared, the previous expression and its result is displayed above it and older expressions are moved upward to make space, until they reach the top of the screen
* Common shortcuts such as Ctrl+A to select all, Ctrl+C to copy, Ctrl+V to paste, etc. should be supported
* The result of each expression is displayed in multiple different formats, including both decimal and hexadecimal representations

The operations supported in arithmetic expression should include addition, subtraction, multiplication, division, square root, cube root, trigonometric functions, left shift, right shift, and possibly powers and logarithms.