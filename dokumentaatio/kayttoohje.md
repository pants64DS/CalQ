# Instructions for use

To run the program, make sure you have Java (at least Java 11, with JavaFX included) installed and run
```
java -jar CalQ.jar
```
(or if the filename of the .jar file in the release happens to be slightly different, type that instead of `CalQ.jar`)

The calculator window contains a single text field and some space below it where the result will be displayed:

<img src="https://cdn.discordapp.com/attachments/808691625408331776/975488584175984680/unknown.png" width="500">

To calculate the result of an expression, type it into the text field and press `Enter`. A valid expression may consist of **simple operands**, **binary operators**, **unary operators** and **functions**, as well as parentheses.

## Simple operands
Operands can be either decimal, hexadecimal or octal numbers. Hexadecimal numbers must be prefixed with `0x` and contain digits from `0` to `9` and from `a` to `f`. Octal numbers must be prefixed with `0` and contain digits from `0` to `7`. Decimal numbers must either start with a digit from `1` to `9` followed by digits from `0` to `9`, or contain a fractional separator. Only decimal and hexadecimal numbers may contain a fractional separator `.`, which is used to separate the integer and fractional parts of the number.

## Binary operators
Binary operators are placed between operands, which can be either simple operands or subexpressions. Both operands are evaluated, and their values are used to calculate the result of the operation. The following binary operators are supported:

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

## Unary operators and functions
Unary operators and functions are placed in front of an operand. The operand is evaluated and some transformation is applied to it to get the result. Both functions like `sqrt` and operators like `+` or `~` are treated the same way, and neither requires parentheses or even a space before the operand. Expressions like `+1`, `+ (1)`, `sqrt(2)` and `sqrt2` are equally valid. The following unary operators are supported:

* `+` for no-op
* `-` for negation
* `~` for bitwise not
* `sin` for sine 
* `cos` for cosine 
* `tan` for tangent 
* `sqrt` for square root 
* `floor` for rounding to the nearest integer below 
* `ceil` for rounding to the nearest integer above 
* `abs` for absolute value 

## Precedence and parentheses
All operators follow the same order of operations as standard C++ (see table [here](https://en.cppreference.com/w/cpp/language/operator_precedence)), and all unary operators and functions are evaluated before binary operators. Parentheses can be used to change the order.

## Precision and rounding error
Since the point of the calculator is to simulate fixed point arithmetic, rounding error is to be expected, and it's **not a bug**. All calculations are currently done in Q12 fixed point numbers, which means that there's no less and no more than 12 bits for the fractional part at all times. However, unlike in most real-world fixed-point arithmetic, the bit width of each number is unbounded, so overflow and underflow don't happen.