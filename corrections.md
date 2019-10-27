>## Preliminary instructions
>
>First check the following:
>>- There is a rendering (in the deposit git)
>>- No cheating, the student must be able to explain his code.
>>- If the program is written in a compilable language, there is
>
>Well a Makefile with at least the rules all, re, and clean. Yes an item from this list is not respected, the notation stop there. Use the appropriate flag. You are encouraged to continue to discuss the project, but the scale is not applied.
>>- Yes
>>- No
---
>## Required Part
>>## Presence of the reduced equation
>>The program takes as parameter or waits on the standard input for an equation and then displays the same equation in reduced form?
>>Is it the right one?
>>>- Yes
>>>- No
>
>>## Format of the reduced equation
>>In the reduced equation, the powers are displayed up to
>>the last non-zero, only once, and one of the two sides
>of the equation is zero?
>>>- Yes
>>>- No
---
>## Management of the entry
>Try several well-formatted but potentially
>poorly managed (null coefficients, negative, not whole ...).
>The program handles them well? (no crash, no mistakes
>calculation, no infinite loop ...).
>IMPORTANT: If you answered no to any of these three
>question, the defense stops there.
>>## Equations of degree 0
>>Enter a possible equation (for example, "5 * X^0 = 5 * X^0").
>Does the program tell you that all real numbers are solution?
>Enter an impossible equation (for example, "4 * X^0 = 8 * X^0 ")
>Does the program tell you that there are no solutions?
>>>- Yes
>>>- No
>
>>## Equations of degree 1
>>Enter an equation of degree 1 (for example: "5 * X^0 = 4 * X^0 + 7 * X^1").
>Does the program show you the solution of the equation?
>Do several tests.
>>>- Yes
>>>- No
>
>>## Equations of degree 2
>>>### Strictly positive discriminator
>>>Enter a degree two equation with a discriminant strictly positive
>>>(for example: "5 * X^0 + 13 * X^1 + 3 * X^2 = 1 * X^0 + 1 * X^1").
>>>The program shows you well that it has a discriminant strictly positive?
>>>The program shows you two Solutions?
>>>These are the good ones?
>>>Do several tests.
>>>>- Yes
>>>>- No
>>
>>>### Discriminant null
>>>Enter an equation of degree two with a discriminant equal to 0
>>>(for example: "6 * X ^ 0 + 11 * X ^ 1 + 5 * X ^ 2 = 1 * X ^ 0 + 1 * X ^ >1").
>>>Does the program show you well that it has a zero discriminator?
>>>Does the program show you a unique solution?
>>>It's here good?
>>>Do several tests.
>>>>- Yes
>>>>- No
>>
>>>### Discriminant strictly negative
>>> Enter an equation of degree two with a discriminator strictly negative
>>>(for example: "5 * X ^ 0 + 3 * X ^ 1 + 3 * X ^ 2 = 1 * X ^ 0 + 0 * X ^ 1 ").
>>>Does the program show you well that it has a strictly negative discriminant?
>>>Does the program show you two complex solutions?
>>>Those are the good?
>>>Do several tests.
>>>>- Yes
>>>>- No
>
>>## Equations of degree 3 or more
>>Enter an equation of degree three or more.
>>The program must refuse to solve the equation.
>>Good after, if the program responds, put the points anyway and make a little hiss admiration, but in any case, the program should not crash.
>>>- Yes
>>>- No