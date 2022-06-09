# Syntax
> :warning: All SimPL programs are expressions

## Untyped Lambda Calculus
$$
\begin{aligned}
e &::= \quad x &\text{(variable name)} \\
 &| \qquad \operatorname{fn} x \Rightarrow e &\text{(function abstraction)} \\
 &| \qquad e_1\ e_2 &\text{(function application)}
\end{aligned}
$$

---

$$
\begin{aligned}
v &::= \quad \{\operatorname{fun}, x, e, E\} &\text{(abstraction value closure)}
\end{aligned}
$$

## Simply-typed Lambda Calculus
$$
\begin{aligned}
e &::= \quad \ldots & \\
 &| \qquad true &\text{(true value)} \\
 &| \qquad false &\text{(false value)} \\
 &| \qquad \operatorname{not} e &\text{(logical not expression)} \\
 &| \qquad e_1 \operatorname{logicop} e_2, \operatorname{logicop} \in \{\operatorname{andalso}, \operatorname{orelse}\} &\text{(logical binary expression)} \\
 &| \qquad \operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3 &\text{(condition)} \\
 & & \\
 &| \qquad n &\text{(integer number value)} \\
 &| \qquad \sim e &\text{(negative expression)} \\
 &| \qquad e_1 \operatorname{arithop} e_2, \operatorname{arithop} \in \{+, -, *, /, \%\} &\text{(arithmetic expression)} \\
 &| \qquad e_1 \operatorname{relop} e_2, \operatorname{relop} \in \{<, <=, >, >=\} &\text{(relation expression)} \\
 &| \qquad e_1 \operatorname{eqop} e_2, \operatorname{eqop} \in \{=, <>\} &\text{(equality expression)}
\end{aligned}
$$

---

$$
\begin{aligned}
v &::= \quad \ldots & \\
 &| \qquad true &\text{(true value)} \\
 &| \qquad false &\text{(false value)} \\
 & & \\
 &| \qquad n &\text{(integer number value)}
\end{aligned}
$$

## Extensions
$$
\begin{aligned}
e &::= \quad \ldots & \\
 &| \qquad \operatorname{let} x=e_1 \operatorname{in} e_2 &\text{(let expression)} \\
 & & \\
 &| \qquad (e_1, e_2) &\text{(pair expression)} \\
 & & \\
 &| \qquad \operatorname{inl} e &\text{(left injection)} \\
 &| \qquad \operatorname{inr} e &\text{(right injection)} \\
 &| \qquad \operatorname{case} e \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2 &\text{(sum case)} \\
 & & \\
 &| \qquad \operatorname{rec} x \Rightarrow e &\text{(recursive function)} \\
 & & \\
 &| \qquad nil &\text{(empty list)} \\
 &| \qquad e_1::e_2 &\text{(list constructor)} \\
 &| \qquad \operatorname{case} e \operatorname{of} nil \Rightarrow e_1 \mid x_1::x_2 \Rightarrow e_2 &\text{(list destructor)} \\
 & & \\
 &| \qquad (e) &\text{(grouping)}
\end{aligned}
$$

---

$$
\begin{aligned}
v &::= \quad \ldots & \\
 &| \qquad (v_1, v_2) &\text{(pair value)} \\
 & & \\
 &| \qquad \operatorname{inl} v &\text{(left injection value)} \\
 &| \qquad \operatorname{inr} v &\text{(right injection value)} \\
 & & \\
 &| \qquad \{\operatorname{rec}, x, e, E\} &\text{(recursion value closure)} \\
 & & \\
 &| \qquad nil &\text{(empty list)} \\
 &| \qquad v_1::v_2 &\text{(list value)}
\end{aligned}
$$

## Imperative
$$
\begin{aligned}
e &::= \quad \ldots & \\
 &| \qquad \operatorname{ref}\ e &\text{(reference creation)} \\
 &| \qquad !e &\text{(dereference)} \\
 &| \qquad e_1 := e_2 &\text{(assignment)} \\
 &| \qquad () &\text{(unit)} \\
 & & \\
 &| \qquad e_1 ; e_2 &\text{(sequence)} \\
 & & \\
 &| \qquad \operatorname{while} e_1 \operatorname{do} e_2 &\text{(while loop)} \\
 &| \qquad <e_1, e_2> &\text{(sequence pair)} \\
 &| \qquad break &\text{(break)} \\
 &| \qquad continue &\text{(continue)}
\end{aligned}
$$

---

$$
\begin{aligned}
v &::= \quad \ldots & \\
 &| \qquad l &\text{(store location value)} \\
 &| \qquad () &\text{(unit value)} \\
 & & \\
 &| \qquad break &\text{(break value)} \\
 &| \qquad continue &\text{(continue value)}
\end{aligned}
$$

