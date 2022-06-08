# Free Variables
> $FV(e)$ denotes all variables which are unbound existing in $e$

## Untyped Lambda Calculus
$$
FV(x) = \{x\}
$$

$$
FV(e_1\ e_2) = FV(e_1) \cup FV(e_2)
$$

$$
FV(\operatorname{fn} x \Rightarrow e) = FV(e) - \{x\}
$$

## Simply-typed Lambda Calculus
$$
FV(true) = FV(false) = \emptyset
$$
> $FV(true) = FV(\lambda t.\lambda f.t) = \emptyset$
> 
> $FV(false) = FV(\lambda t.\lambda f.f) = \emptyset$

$$
FV(\operatorname{not} e) = FV(e)
$$
> $FV(\operatorname{not} e) = FV\left( (\lambda x.\lambda a.\lambda b.x\ b\ a)\ e \right) = FV(e)$

$$
FV(e_1 \operatorname{logicop} e_2) = FV(e_1) \cup FV(e_2), \operatorname{logicop} \in \{\operatorname{andalso}, \operatorname{orelse}\}
$$
> $FV(e_1 \operatorname{andalso} e_2) = FV\left( (\lambda x.\lambda y.x\ y\ false)\ e_1\ e_2 \right) = FV(e_1) \cup FV(e_2)$
> 
> the other is similar

$$
FV(\operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3) = FV(e_1) \cup FV(e_2) \cup FV(e_3)
$$
> $FV(\operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3) = FV\left( (\lambda x.\lambda then.\lambda else.x\ then\ else)\ e_1\ e_2\ e_3 \right) = FV(e_1) \cup FV(e_2) \cup FV(e_3)$

$$
FV(n) = \emptyset
$$
> for natural number: $FV(0) = FV(\lambda f.\lambda x.x) = \emptyset$, $FV(1) = FV(\lambda f.\lambda x.f\ x) = \emptyset$, $FV(n) = FV(\lambda f.\lambda x.f^{n}\ x) = \emptyset$
> 
> for integer number: $FV(i) = FV(\operatorname{zero}\ (\operatorname{pair}\ n_1\ n_2)) = FV(\operatorname{zero}) \cup FV(\operatorname{pair}) \cup FV(n_1) \cup FV(n_2) = \emptyset$

$$
FV(\sim e) = FV(e)
$$
> for integer number: $FV(\sim e) = FV\left( (\lambda i.\operatorname{pair}\ (\operatorname{snd}\ i)\ (\operatorname{fst}\ i))\ e \right) = FV(\operatorname{pair}) \cup FV(\operatorname{fst}) \cup FV(\operatorname{snd}) \cup FV(e) = FV(e)$

$$
FV(e_1 \operatorname{arithop} e_2) = FV(e_1) \cup FV(e_2), \operatorname{arithop} \in \{+, -, *, /, \%\}
$$
> for natural number: $FV(e_1 + e_2) = FV\left( (\lambda m.\lambda n.\lambda f.\lambda x.m\ f\ (n\ f\ x))\ e_1\ e_2 \right) = FV\left( \lambda m.\lambda n.\lambda f.\lambda x.m\ f\ (n\ f\ x) \right) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> for integer number: $FV(e_1 + e_2) = FV( (\lambda i.\lambda j.\operatorname{zero}\ \left( \operatorname{pair}\ (\operatorname{add}\ (\operatorname{fst}\ i)\ (\operatorname{fst}\ j))\ (\operatorname{add}\ (\operatorname{snd}\ i)\ (\operatorname{snd}\ j)) \right))\ e_1\ e_2 ) = FV(\operatorname{zero}) \cup FV(\operatorname{add}) \cup FV(\operatorname{pair}) \cup FV(fst) \cup FV(snd) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> the others are similar

$$
FV(e_1 \operatorname{relop} e_2) = FV(e_1) \cup FV(e_2), \operatorname{relop} \in \{<, <=, >, >=\}
$$
> for natural number: $FV(e_1 < e_2) = FV\left( (\lambda m.\lambda n.\operatorname{andalso}\ (\operatorname{leq}\ m\ n)\ (\operatorname{not}\ (\operatorname{equal}\ m\ n)))\ e_1\ e_2 \right) = FV(\operatorname{andalso}) \cup FV(\operatorname{leq}) \cup FV(\operatorname{equal}) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> for integer number: $FV(e_1 < e_2) = FV\left( (\lambda i.\lambda j.\operatorname{less}\ (\operatorname{add}\ (\operatorname{fst}\ i)\ (\operatorname{snd}\ j))\ (\operatorname{add}\ (\operatorname{snd}\ i)\ (\operatorname{fst}\ j)))\ e_1\ e_2 \right) = FV(\operatorname{less}) \cup FV(\operatorname{fst}) \cup FV(\operatorname{snd}) \cup FV(\operatorname{add}) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> the others are similar

$$
FV(e_1 \operatorname{eqop} e_2) = FV(e_1) \cup FV(e_2), \operatorname{eqop} \in \{=, <>\}
$$
> for natural number: $FV(e_1 = e_2) = FV\left( (\lambda m.\lambda n.\operatorname{andalso}\ (\operatorname{leq}\ m\ n)\ (\operatorname{leq}\ n\ m))\ e_1\ e_2 \right) = FV(\operatorname{andalso}) \cup FV(\operatorname{leq}) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> for integer number: $FV(e_1 = e_2) = FV\left( (\lambda i.\lambda j.\operatorname{equal}\ (\operatorname{add}\ (\operatorname{fst}\ i)\ (\operatorname{snd}\ j))\ (\operatorname{add}\ (\operatorname{snd}\ i)\ (\operatorname{fst}\ j)))\ e_1\ e_2 \right) = FV(\operatorname{equal}) \cup FV(\operatorname{add}) \cup FV(\operatorname{fst}) \cup FV(\operatorname{snd}) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$
> 
> the others are similar

## Extensions
$$
FV(\operatorname{let} x = e_1 \operatorname{in} e_2 \operatorname{end}) = FV(e1) \cup (FV(e2)-\{x\})
$$
> $FV(\operatorname{let} x = e_1 \operatorname{in} e_2 \operatorname{end}) = FV((\lambda x.e_2)\ e_1) = FV(e_1) \cup (FV(e_2) - \{x\})$

$$
FV((e_1, e_2)) = FV(e_1) \cup FV(e_2)
$$
> $FV((e_1, e_2)) = FV(\operatorname{pair}\ e_1\ e_2) = FV(\lambda f.\lambda s.\lambda b.b\ f\ s) \cup FV(e_1) \cup FV(e_2) = FV(e_1) \cup FV(e_2)$

$$
FV(\operatorname{rec} x \Rightarrow e) = FV(e) - \{x\}
$$
> $FV(\operatorname{rec} x \Rightarrow e) = FV(\operatorname{fix}\ \lambda x.e) = FV( \lambda f.\left( (\lambda x.f\ (\lambda y.x\ x\ y))\ (\lambda x.f\ (\lambda y.x\ x\ y)) \right) ) \cup FV(\lambda x.e) = FV(e) - \{x\}$

$$
FV(nil) = \emptyset
$$
> unknown

$$
FV(e_1 :: e_2) = FV(e_1) \cup FV(e_2)
$$
> unknown

$$
FV((e)) = FV(e)
$$
> unknown

## Imperative
$$
FV(\operatorname{ref}\ e) = FV(e)
$$
> unknown

$$
FV(!e) = FV(e)
$$
> unknown

$$
FV(e_1 := e_2) = FV(e_1) \cup FV(e_2)
$$
> unknown

$$
FV(()) = \emptyset
$$
> unknown

$$
FV(e_1; e_2) = FV(e_1) \cup FV(e_2)
$$
> unknown

$$
FV(\operatorname{while} e_1 \operatorname{do} e_2) = FV(e_1) \cup FV(e_2)
$$
> $FV(\operatorname{while} e_1 \operatorname{do} e_2) = FV(\operatorname{if} e_1 \operatorname{then} (e_2;\operatorname{while} e_1 \operatorname{do} e_2) \operatorname{else} ()) = FV(e_1) \cup FV(e_2)$

$$
FV(<e_1, e_2>) = FV(e_1) \cup FV(e_2)
$$
> unknown

$$
FV(break) = \emptyset
$$
> unknown

$$
FV(continue) = \emptyset
$$
> unknown


# All Variables
> $Vars(e)$ denotes all variables existing in $e$

## Untyped Lambda Calculus
$$
Vars(x) = \{x\}
$$

$$
Vars(e_1\ e_2) = Vars(e_1) \cup Vars(e_2)
$$

$$
Vars(\operatorname{fn} x \Rightarrow e) = Vars(e) \cup \{x\}
$$

## Simply-typed Lambda Calculus
$$
Vars(true) = Vars(false) = \emptyset
$$
> unknown

$$
Vars(\operatorname{not}\ e) = Vars(e)
$$
> unknown

$$
Vars(e_1 \operatorname{logicop} e_2) = Vars(e_1) \cup Vars(e_2), \operatorname{logicop} \in \{\operatorname{andalso}, \operatorname{orelse}\}
$$
> unknown

$$
Vars(\operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3) = Vars(e_1) \cup Vars(e_2) \cup Vars(e_3)
$$
> unknown

$$
Vars(n) = \emptyset=
$$
> unknown

$$
Vars(\sim e) = Vars(e)
$$
> unknown

$$
Vars(e_1 \operatorname{arithop} e_2) = Vars(e_1) \cup Vars(e_2), \operatorname{arithop} \in \{+, -, *, /, \%\}
$$
> unknown

$$
Vars(e_1 \operatorname{relop} e_2) = Vars(e_1) \cup Vars(e_2), \operatorname{relop} \in \{<, <=, >, >=\}
$$
> unknown

$$
Vars(e_1 \operatorname{eqop} e_2) = Vars(e_1) \cup Vars(e_2), \operatorname{eqop} \in \{=, <>\}
$$
> unknown

## Extensions
$$
Vars(\operatorname{let} x = e_1 \operatorname{in} e_2 \operatorname{end}) = Vars(e1) \cup (Vars(e2) \cup \{x\})
$$
> $Vars(\operatorname{let} x = e_1 \operatorname{in} e_2 \operatorname{end}) = Vars((\lambda x.e_2)\ e_1) = Vars(e_1) \cup (Vars(e_2) \cup \{x\})$

$$
Vars((e_1, e_2)) = Vars(e_1) \cup Vars(e_2)
$$
> unknown

$$
Vars(\operatorname{rec} x \Rightarrow e) = Vars(e) \cup \{x\}
$$
> unknown

$$
Vars(nil) = \emptyset
$$
> unknown

$$
Vars(e_1 :: e_2) = Vars(e_1) \cup Vars(e_2)
$$
> unknown

## Imperative
$$
Vars(\operatorname{ref}\ e) = Vars(e)
$$
> unknown

$$
Vars(!e) = Vars(e)
$$
> unknown

$$
Vars(e_1 := e_2) = Vars(e_1) \cup Vars(e_2)
$$
> unknown

$$
Vars(()) = \emptyset
$$
> unknown

$$
Vars(e_1; e_2) = Vars(e_1) \cup Vars(e_2)
$$
> unknown

$$
Vars(\operatorname{while} e_1 \operatorname{do} e_2) = Vars(e_1) \cup Vars(e_2)
$$
> $Vars(\operatorname{while} e_1 \operatorname{do} e_2) = Vars(\operatorname{if} e_1 \operatorname{then} (e_2;\operatorname{while} e_1 \operatorname{do} e_2) \operatorname{else} ()) = Vars(e_1) \cup Vars(e_2)$

$$
Vars(<e_1, e_2>) = Vars(e_1) \cup Vars(e_2)
$$
> unknown

$$
Vars(break) = \emptyset
$$
> unknown

$$
Vars(continue) = \emptyset
$$
> unknown
