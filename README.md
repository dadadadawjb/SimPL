# SimPL
<img src="assets/simpl.png" alt="SimPL" width="10%" >

An *interpreter* for the programming language **SimPL** (pronounced *simple*).
SimPL is a simplified dialect of ML, which can be used for both *functional* and *imperative* programming.

## Support Features
* Pure lambda calculus functional features (variable, function abstraction, function application)
* Simply-typed lambda calculus with extensions (boolean, integer, pair, ~~tuple~~, ~~record~~, sum, ~~variant~~, recursion, ~~let-recursion~~, list, pattern match)
* Imperative programming features (reference, dereference, assignment, while loop, break and continue, ~~error~~, ~~array~~, print)
* Principal type inference with constraint typing rules and unification algorithm
* Substitution based and universal polymorphism based let-polymorphism
* ~~Abstract data types of existential polymorphism~~
* ~~Overloading of ad-hoc polymorphism~~
* Operational semantics including both environment model and memory store
* Predefined generic programming template library functions of universal polymorphism
* Predefined programming computable functions
* ~~Reference counting~~, mark-and-sweep and copy collection based garbage collection
* Rich example programs

## Get Started
```bash
# normal case
java -jar SimPL.jar <filepath>

# test all examples provided
java -jar SimPL.jar examples    # put under the root of the repository
```

## Taste
```ML
(* factorial by functional recursion *)
let fact = rec f => fn x => if x=1 then 1 else x * (f (x-1))
in fact 6
end
(* int *)
(* ==> 720 *)
```

```ML
(* factorial by imperative recursion *)
let x = ref 6 in
  let fact = rec f => fn x => if !x=1 then 1 else (!x) * ((x:=!x-1) ; (f x)) in
    fact x
  end
end
(* int *)
(* ==> 720 *)
```

```ML
(* factorial by imperative iteration *)
let x = ref 6 in
  let result = ref 1 in
    let fact = fn x => 
      (while !x <> 1 do
        (result := !x * !result; x := !x - 1));
      !result
    in fact x end
  end
end
(* int *)
(* ==> 720 *)
```

```ML
(* factorial by pair library functions *)
let fact = rec f => fn p => 
  if (fst p) <> 1 then
    f ((fst p) - 1, (snd p) * (fst p))
  else
    (snd p)
in fact (6, 1) end
(* int *)
(* ==> 720 *)
```

```ML
(* factorial by list pattern match *)
let format = rec f => fn n => 
  if n=1 then (1::nil)
  else (n::(f (n-1)))
in
  let fact = rec f' => fn l => 
    (case l of nil => 1 | x1::x2 => x1 * (f' x2))
  in
    fact (format 6)
  end
end
(* int *)
(* ==> 720 *)
```

```ML
(* factorial by programming computable functions *)
let plus = rec p =>
  fn x => fn y => if iszero x then y else p (pred x) (succ y)
in
  let times = rec t =>
    fn x => fn y => if iszero x then 0 else plus y (t (pred x) y)
  in
    let fact = rec f =>
      fn n => if iszero n then 1 else times n (f (pred n))
    in
      fact 6
    end
  end
end
(* int *)
(* ==> 720 *)
```

## More Details
* [Lexical definition](doc/Lexical.md)
* [Syntax](doc/Syntax.md)
* [Types](doc/Types.md)
* [Free variables and all variables calculation](doc/FreeVariables-AllVariables.md)
* [Substitution](doc/Substitution.md)
* [Operational semantics](doc/OperationalSemantics.md)
* [Typing](doc/Typing.md)
* [Unification](doc/Unification.md)

## TODO List
- [x] basic skeleton
  - [x] lexical definition
  - [x] syntax definition
  - [x] typing definition
  - [x] value definition
  - [x] expression definition
  - [x] `typecheck()`
  - [x] `eval()`
- [ ] more features
  - [ ] ~~tuple~~
  - [ ] ~~record~~
  - [x] sum
  - [ ] ~~variant~~
  - [x] case
  - [x] break and continue
  - [ ] ~~array~~
  - [x] print, println
  - [ ] ~~letrec~~
  - [ ] ~~error, raise, try~~
- [ ] optimization
  - [x] substitution based let-polymorphism
  - [x] universal polymorphism based let-polymorphism
  - [x] universal parametric polymorphism
  - [ ] ~~existential parametric polymorphism~~
  - [ ] ~~subtype polymorphism~~
  - [ ] ~~ad-hoc polymorphism~~
  - [ ] ~~reference counting based garbage collection~~
  - [x] mark-and-sweep based garbage collection
  - [x] copy collection garbage collection

## Note
The skeleton is taken from [CS383](https://www.cs.sjtu.edu.cn/~kzhu/cs383/) by [Kenny Zhu](https://www.cs.sjtu.edu.cn/~kzhu/).