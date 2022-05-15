# A Simple Fixed-Point Calculator

The goal of this project was to make a simple and convenient [SpeedCrunch](https://bitbucket.org/heldercorreia/speedcrunch/src/master/)-style calculator for fixed-point arithmetic. The GUI consists of a single window, which contains a text field at the bottom.

## The app supports the following features:
* The user can write a fixed-point arithmetic expression made of ASCII characters to the text field
* When the user presses Enter, the result of the expression is displayed
* The expression may contain several different binary and unary operators (see below)
* Numbers in the expression are hexadecimal when prefixed with "0x", octal when prefixed with "0" or decimal when there is no prefix
* Operators follow [the same precedence rules as standard C++](https://en.cppreference.com/w/cpp/language/operator_precedence)
* Like in math, the precedence can be changed using parentheses
* Functions like `sin`, `cos`, `sqrt`, etc. 
* The result is calculated using Q12 fixed-point arithmetic (i.e. with 12 fractional bits)
* The text field supports common shortcuts such as Ctrl+A to select all, Ctrl+C to copy, Ctrl+V to paste, etc.
* The result of the expression is displayed in decimal

## Supported binary operators:
* `+` for addition
* `-` for subtraction
* `*` for multiplication
* `/` for division
* `%` for remainder
* `<<` for bitwise left shift
* `>>` for bitwise right shift
* `&` for bitwise and
* `|` for bitwise or
* `^` for bitwise xor

## Supported unary operators and functions:
* `+` for no-op
* `-` for negation
* `~` for bitwise not
* `sin` for sine 
* `cos` for sosine 
* `tan` for tangent 
* `sqrt` for square root 
* `floor` for rounding to the nearest integer below 
* `ceil` for rounding to the nearest integer above 
* `abs` for absolute value 
