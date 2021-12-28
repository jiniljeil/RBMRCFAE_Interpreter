# Program: RBMRCFAE syntax Parser/Interpreter

#### Writer: Jinil Kim taking [Programming Language Theory]  (JC Nam Professor) 

## Introduction 
It is a program that calculates the Expressions with Parser and Interpreter. If you enter a concrete syntax, it is changed to abstract syntax by using a parser. After that, abstract syntax is interpreted by the Interpreter. As a result, you can see the result by calculating the expression entered by the user.  
It supports the expressions which are RCFAE and RBMFAE. Thus, you can execute the recursion, call-by-value, and call-by-reference.

## How to compile 

1. Enter a directory named the RBMRCFAE. If you enter the command to compile, a class file will be made.

Compile Command: javac [Directory]/[Java File] 	

```
$> javac plt/*.java ast/*.java lfae/*.java rbmfae/*.java rcfae/*.java
```

2. After the compile process is finished, you can run the program with command line argument method.

### 1) If you want to use only parser 

#### Execution Command (Only parser): 
<strong> java plt.Main -p [expression] </strong>  

```
$> java plt.Main -p "{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} b}}}}"

(app (fun 'swap (app (fun 'a (app (fun 'b (seqn (app (app (id 'swap) (id 'a)) (id 'b)) (id 'b))) (num 20))) (num 10))) (refun 'x (refun 'y (app (fun 'z (seqn (setvar 'x (id 'y)) (setvar 'y (id 'z)))) (id 'x)))))

$> java plt.Main -p "{rec {fac {fun {n} {if0 n 1 {* n {fac {- n 1}}}}}} {fac 5}}"

(rec 'fac (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (id 'fac) (sub (id 'n) (num 1)))))) (app (id 'fac) (num 5)))
```

### 2) If you want to get the result of entered expressions

#### Execution Command (Parser & Interpreter): 
<strong> java plt.Main [expression] </strong> 

```
$> java plt.Main "{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} b}}}}"

(v*s (numV 10) (aSto 3 (numV 10) (aSto 2 (numV 20) (aSto 4 (numV 10) (aSto 3 (numV 20) (aSto 2 (numV 10) (aSto 1 (refclosV x (refun 'y (app (fun 'z (seqn (setvar 'x (id 'y)) (setvar 'y (id 'z)))) (id 'x))) (mtSub)) (mtSto))))))))

$> java plt.Main "{with {swap {refun {x} {refun {y} {with {z x} {seqn {setvar x y} {setvar y z}}}}}} {with {a 10} {with {b 20} {seqn {{swap a} b} a}}}}"

(v*s (numV 20) (aSto 3 (numV 10) (aSto 2 (numV 20) (aSto 4 (numV 10) (aSto 3 (numV 20) (aSto 2 (numV 10) (aSto 1 (refclosV x (refun 'y (app (fun 'z (seqn (setvar 'x (id 'y)) (setvar 'y (id 'z)))) (id 'x))) (mtSub)) (mtSto))))))))

$> java plt.Main "{rec {fac {fun {n} {if0 n 1 {* n {fac {- n 1}}}}}} {fac 5}}"

(v*s (numV 120) (aSto 7 (numV 0) (aSto 6 (numV 1) (aSto 5 (numV 2) (aSto 4 (numV 3) (aSto 3 (numV 4) (aSto 2 (numV 5) (aSto 1  (mtSto)))))))))

$> java plt.Main "{with {fac {with {facX {fun {facY} {fun {n} {if0 n 1 {* n {{facY facY} {- n 1}}}}}}} {facX facX}}} {fac 10}}"

(v*s (numV 3628800) (aSto 24 (numV 0) (aSto 23 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 22 (numV 1) (aSto 21 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 20 (numV 2) (aSto 19 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 18 (numV 3) (aSto 17 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 16 (numV 4) (aSto 15 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 14 (numV 5) (aSto 13 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 12 (numV 6) (aSto 11 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 10 (numV 7) (aSto 9 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 8 (numV 8) (aSto 7 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 6 (numV 9) (aSto 5 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 4 (numV 10) (aSto 3 (closureV n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1))))) (aSub 'facY 2 (mtSub))) (aSto 2 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (aSto 1 (closureV facY (fun 'n (if0 (id 'n) (num 1) (mul (id 'n) (app (app (id 'facY) (id 'facY)) (sub (id 'n) (num 1)))))) (mtSub)) (mtSto))))))))))))))))))))))))))
```

## BNF Design of RBMRCFAE 

<pre> 
&lt;RBMRCFAE&gt; ::= &lt;num&gt; 
          | {+ &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;}
          | {- &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;} 
          | {* &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;} 
          | &lt;id&gt; 
          | {if0 &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;}
          | {rec {&lt;id&gt; &lt;RBMRCFAE&gt;} &lt;RBMRCFAE&gt;}
          | {fun {&lt;id&gt;} &lt;RBMRCFAE&gt;} 
          | {refun {&lt;id&gt;} &lt;RBMRCFAE&gt;} 
          | {&lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;} 
          | {newbox &lt;RBMRCFAE&gt;}
          | {setbox &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;}
          | {openbox &lt;RBMRCFAE&gt;}
          | {seqn &lt;RBMRCFAE&gt; &lt;RBMRCFAE&gt;}
          | {setvar &lt;id&gt; &lt;RBMRCFAE&gt;}
</pre>



