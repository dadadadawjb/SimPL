# Operational Semantics
## Untyped Lambda Calculus
$$
\frac{E(x) = v}{E ; (M, p, x) \Downarrow (M, p, v)}(EEM-Name2)
$$

$$
\frac{}{E ; (M, p, \operatorname{fn} x \Rightarrow e) \Downarrow (M, p, \{\operatorname{fun}, x, e, E\})}(EEM-Fn)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', \{\operatorname{fun}, x, e, E_1\}) \qquad E ; (M', p', e_2) \rightarrow (M'', p'', v_2) \qquad E_1, x:v_2 ; (M'', p'', e) \rightarrow (M''', p''', v)}{E ; (M, p, e_1\ e_2) \rightarrow (M''', p''', v)}(EEM-App)
$$

## Simply-typed Lambda Calculus
$$
\frac{E ; (M, p, e) \Downarrow (M', p', true)}{E ; (M, p, \operatorname{not}\ e) \Downarrow (M', p', false)}(EEM-Not1)
$$

$$
\frac{E ; (M, p, e) \Downarrow (M', p', false)}{E ; (M, p, \operatorname{not}\ e) \Downarrow (M', p', true)}(EEM-Not2)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', true) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v)}{E ; (M, p, e_1 \operatorname{andalso} e_2) \Downarrow (M'', p'', v)}(EEM-AndAlso1)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', false)}{E ; (M, p, e_1 \operatorname{and} e_2) \Downarrow (M', p', false)}(EEM-AndAlso2)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', true)}{E ; (M, p, e_1 \operatorname{orelse} e_2) \Downarrow (M', p', true)}(EEM-OrElse1)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', false) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v)}{E ; (M, p, e_1 \operatorname{or} e_2) \Downarrow (M'', p'', v)}(EEM-OrElse2)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', true) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v)}{E ; (M, p, \operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3) \Downarrow (M'', p'', v)}(EEM-Cond1)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', false) \qquad E ; (M', p', e_3) \Downarrow (M'', p'', v)}{E ; (M, p, \operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3) \Downarrow (M'', p'', v)}(EEM-Cond2)
$$

$$
\frac{E ; (M, p, e) \Downarrow (M', p', v) \qquad v' = -v}{E ; (M, p, \sim e) \Downarrow (M', p', v')}(EEM-Neg)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2) \qquad v = v_1 \operatorname{arithop} v_2}{E ; (M, p, e_1 \operatorname{arithop} e_2) \Downarrow (M'', p'', v)}(EEM-Arith1), \operatorname{arithop} \in \{+, -, *\}
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2) \qquad v_2 \neq 0 \qquad v = v_1 \operatorname{arithop} v_2}{E ; (M, p, e_1 \operatorname{arithop} e_2) \Downarrow (M'', p'', v)}(EEM-Arith2), \operatorname{arithop} \in \{/, \%\}
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2) \qquad v = v_1 \operatorname{relop} v_2}{E ; (M, p, e_1 \operatorname{relop} e_2) \Downarrow (M'', p'', v)}(EEM-Rel), \operatorname{relop} \in \{<, <=, >, >=\}
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2) \qquad v = v_1 \operatorname{eqop} v_2}{E ; (M, p, e_1 \operatorname{eqop} e_2) \Downarrow (M'', p'', v)}(EEM-Eq), \operatorname{eqop} \in \{=, <>\}
$$

## Extensions
$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E, x:v_1 ; (M', p', e_2) \Downarrow (M'', p'', v_2)}{E ; (M, p, \operatorname{let} x=e_1 \operatorname{in} e_2 \operatorname{end}) \Downarrow (M'', p'', v_2)}(EEM-Let)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2)}{E ; (M, p, (e_1, e_2)) \Downarrow (M'', p'', (v_1, v_2))}(EEM-Pair)
$$

$$
\frac{E, x : \{\operatorname{rec}, x, e, E\} ; (M, p, e) \Downarrow (M', p', v)}{E ; (M, p, \operatorname{rec} x \Rightarrow e) \Downarrow (M', p', v)}(EEM-Rec)
$$

$$
\frac{E(x) = \{\operatorname{rec}, x_1, e_1, E_1\} \qquad E_1 ; (M, p, \operatorname{rec} x_1 \Rightarrow e_1) \Downarrow (M', p', v)}{E ; (M, p, x) \Downarrow (M', p', v)}(EEM-Name1)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', v_1) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2)}{E ; (M, p, e_1::e_2) \Downarrow (M'', p'', v_1::v_2)}(EEM-Cons)
$$

$$
\frac{E ; (M, p, e) \Downarrow (M', p', v)}{E ; (M, p, (e)) \Downarrow (M', p', v)}(EEM-Group)
$$

## Imperative
$$
\frac{E ; \left( M, p+1, e \right) \Downarrow \left( M'', p',  v \right) \qquad M' = M'', p:v}{E ; \left( M, p, \operatorname{ref} e \right) \Downarrow \left( M', p', p \right)}(EEM-Ref)
$$

$$
\frac{E ; \left( M, p, e \right) \Downarrow \left( M', p', p_1 \right) \qquad v = M'(p_1)}{E ; \left( M, p, !e \right) \Downarrow \left( M', p', v \right)}(EEM-Deref)
$$

$$
\frac{E ; \left( M, p, e_1 \right) \Downarrow \left( M', p', p_1 \right) \qquad E ; (M', p', e_2) \Downarrow (M''', p'', v) \qquad M'' = M''', p_1:v}{E ; \left( M, p, e_1 := e_2 \right) \Downarrow \left( M'', p'', () \right)}(EEM-Assign)
$$

$$
\frac{E ; \left( M, p, e_1 \right) \Downarrow \left( M', p', () \right) \qquad E ; \left( M', p', e_2 \right) \Downarrow \left( M'', p'', v_2 \right)}{E ; \left( M, p, e_1;e_2 \right) \Downarrow \left( M'', p'', v_2 \right)}(EEM-Seq)
$$

$$
\frac{E ; \left( M, p, e_1 \right) \Downarrow \left( M', p', break \right)}{E ; \left( M, p, e_1;e_2 \right) \Downarrow \left( M', p', break \right)}(EEM-SeqBreak)
$$

$$
\frac{E ; \left( M, p, e_1 \right) \Downarrow \left( M', p', continue \right)}{E ; \left( M, p, e_1;e_2 \right) \Downarrow \left( M', p', continue \right)}(EEM-SeqContinue)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', true) \qquad E ; (M', p', <e_2, \operatorname{while} e_1 \operatorname{do} e_2>) \Downarrow (M'', p'', v)}{E ; \left( M, p, \operatorname{while} e_1 \operatorname{do} e_2 \right) \Downarrow \left( M'', p'', v \right)}(EEM-Loop1)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', false)}{E ; \left( M, p, \operatorname{while} e_1 \operatorname{do} e_2 \right) \Downarrow \left( M', p', () \right)}(EEM-Loop2)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', break)}{E ; (M, p, <e_1, e_2>) \Downarrow (M', p', ())}(EEM-Break)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', continue) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2)}{E ; (M, p, <e_1, e_2>) \Downarrow (M'', p'', v_2)}(EEM-Continue)
$$

$$
\frac{E ; (M, p, e_1) \Downarrow (M', p', ()) \qquad E ; (M', p', e_2) \Downarrow (M'', p'', v_2)}{E ; (M, p, <e_1, e_2>) \Downarrow (M'', p'', v_2)}(EEM-SeqPairUnit)
$$
