# Substitution
## Untyped Lambda Calculus
$$
x[e/x] = e
$$

$$
y[e/x] = y, \text{if } y \neq x
$$

$$
(e_1\ e_2)[e/x] = e_1[e/x]\ e_2[e/x]
$$

$$
(\operatorname{fn} x \Rightarrow e)[e'/x] = \operatorname{fn} x \Rightarrow e
$$

$$
(\operatorname{fn} y \Rightarrow e)[e'/x] = 
\begin{cases}
\operatorname{fn} y \Rightarrow (e[e'/x]) &\text{if } y \neq x \text{ and } (y \notin FV(e') \text{ or } x \notin FV(e)) \\
\operatorname{fn} z \Rightarrow (e[z/y][e'/x]) &\text{if } y \neq x \text{ and } y \in FV(e') \text{ and } x \in FV(e) \text{ and } z \notin FV(e') \cup FV(e)
\end{cases}
$$

## Simply-typed Lambda Calculus
$$
true[e/x] = true
$$

$$
false[e/x] = false
$$

$$
(\operatorname{not} e)[e'/x] = \operatorname{not} e[e'/x]
$$

$$
(e_1 \operatorname{logicop} e_2)[e/x] = e_1[e/x] \operatorname{logicop} e_2[e/x], \operatorname{logicop} \in \{\operatorname{andaslo}, \operatorname{orelse}\}
$$

$$
(\operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3)[e/x] = \operatorname{if} e_1[e/x] \operatorname{then} e_2[e/x] \operatorname{else} e_3[e/x]
$$

$$
n[e/x] = n
$$

$$
(\sim e)[e'/x] = \sim (e[e'/x])
$$

$$
(e_1 \operatorname{arithop} e_2)[e/x] = e_1[e/x] \operatorname{arithop} e_2[e/x], \operatorname{arithop} \in \{+, -, *, /, \%\}
$$

$$
(e_1 \operatorname{relop} e_2)[e/x] = e_1[e/x] \operatorname{relop} e_2[e/x], \operatorname{relop} \in \{<, <=, >, >=\}
$$

$$
(e_1 \operatorname{eqop} e_2)[e/x] = e_1[e/x] \operatorname{eqop} e_2[e/x], \operatorname{eqop} \in \{=, <>\}
$$

## Extensions
$$
(\operatorname{let} x=e_1 \operatorname{in} e_2 \operatorname{end})[e/x] = \operatorname{let} x=e_1[e/x] \operatorname{in} e_2 \operatorname{end}
$$

$$
(\operatorname{let} y=e_1 \operatorname{in} e_2 \operatorname{end})[e/x] = 
\begin{cases}
\operatorname{let} y=e_1[e/x] \operatorname{in} e_2[e/x] \operatorname{end} &\text{if } y \neq x \text{ and } (y \notin FV(e) \text{ or } x \notin FV(e_2)) \\
\operatorname{let} z=e_1[e/x] \operatorname{in} e_2[z/y][e/x] \operatorname{end} &\text{if } y \neq x \text{ and } y \in FV(e) \text{ and } x \in FV(e_2) \text{ and } z \notin FV(e) \cup FV(e_2)
\end{cases}
$$

$$
(e_1, e_2)[e/x] = (e_1[e/x], e_2[e/x])
$$

$$
(\operatorname{inl} e)[e'/x] = \operatorname{inl} e[e'/x]
$$

$$
(\operatorname{inr} e)[e'/x] = \operatorname{inr} e[e'/x]
$$

$$
(\operatorname{case} e \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2)[e'/x_1] = \operatorname{case} e[e'/x_1] \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2[e'/x_1]
$$

$$
(\operatorname{case} e \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2)[e'/x_2] = \operatorname{case} e[e'/x_2] \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1[e'/x_2] \mid \operatorname{inr} x_2 \Rightarrow e_2
$$

$$
(\operatorname{case} e \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2)[e'/x] = 
\begin{cases}
\operatorname{case} e[e'/x] \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1[e'/x] \mid \operatorname{inr} x_2 \Rightarrow e_2[e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } (x_1 \notin FV(e') \text{ or } x \notin FV(e_1)) \text{ and } (x_2 \notin FV(e') \text{ or } x \notin FV(e_2)) \\
\operatorname{case} e[e'/x] \operatorname{of} \operatorname{inl} z_1 \Rightarrow e_1[z_1/x_1][e'/x] \mid \operatorname{inr} x_2 \Rightarrow e_2[e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } x_1 \in FV(e') \text{ and } x \in FV(e_1) \text{ and } (x_2 \notin FV(e') \text{ or } x \notin FV(e_2)) \text{ and } z_1 \notin FV(e') \cup FV(e_1) \\
\operatorname{case} e[e'/x] \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1[e'/x] \mid \operatorname{inr} z_2 \Rightarrow e_2[z_2/x_2][e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } (x_1 \notin FV(e') \text{ or } x \notin FV(e_1)) \text{ and } x_2 \in FV(e') \text{ and } x \in FV(e_2) \text{ and } z_2 \notin FV(e') \cup FV(e_2) \\
\operatorname{case} e[e'/x] \operatorname{of} \operatorname{inl} z_1 \Rightarrow e_1[z_1/x_1][e'/x] \mid \operatorname{inr} z_2 \Rightarrow e_2[z_2/x_2][e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } x_1 \in FV(e') \text{ and } x \in FV(e_1) \text{ and } x_2 \in FV(e') \text{ and } x \in FV(e_2) \text{ and } z_1 \notin FV(e') \cup FV(e_1) \text{ and } z_2 \notin FV(e') \cup FV(e_2)
\end{cases}
$$

$$
(\operatorname{rec} x \Rightarrow e)[e'/x] = \operatorname{rec} x \Rightarrow e
$$

$$
(\operatorname{rec} y \Rightarrow e)[e'/x] = 
\begin{cases}
\operatorname{rec} y \Rightarrow (e[e'/x]) &\text{if } y \neq x \text{ and } (y \notin FV(e') \text{ or } x \notin FV(e)) \\
\operatorname{rec} z \Rightarrow (e[z/y][e'/x]) &\text{if } y \neq x \text{ and } y \in FV(e') \text{ and } x \in FV(e) \text{ and } z \notin FV(e') \cup FV(e)
\end{cases}
$$

$$
nil[e/x] = nil
$$

$$
(e_1 :: e_2)[e/x] = e_1[e/x] :: e_2[e/x]
$$

$$
(\operatorname{case} e \operatorname{of} nil \Rightarrow e_1 \mid x_1::x_2 \Rightarrow e_2)[e'/x_1] = \operatorname{case} e[e'/x_1] \operatorname{of} nil \Rightarrow e_1[e'/x_1] \mid x_1::x_2 \Rightarrow e_2
$$

$$
(\operatorname{case} e \operatorname{of} nil \Rightarrow e_1 \mid x_1::x_2 \Rightarrow e_2)[e'/x_2] = \operatorname{case} e[e'/x_2] \operatorname{of} nil \Rightarrow e_1[e'/x_2] \mid x_1::x_2 \Rightarrow e_2
$$

$$
(\operatorname{case} e \operatorname{of} nil \Rightarrow e_1 \mid x_1::x_2 \Rightarrow e_2)[e'/x] = 
\begin{cases}
\operatorname{case} e[e'/x] \operatorname{of} nil \Rightarrow e_1[e'/x] \mid x_1::x_2 \Rightarrow e_2[e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } (x_1 \notin FV(e') \text{ or } x \notin FV(e_1)) \text{ and } (x_2 \notin FV(e') \text{ or } x \notin FV(e_2)) \\
\operatorname{case} e[e'/x] \operatorname{of} nil \Rightarrow e_1[e'/x] \mid z_1::x_2 \Rightarrow e_2[z_1/x_1][e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } x_1 \in FV(e') \text{ and } x \in FV(e_1) \text{ and } (x_2 \notin FV(e') \text{ or } x \notin FV(e_2)) \text{ and } z_1 \notin FV(e') \cup FV(e_2) \cup \{x_2\} \\
\operatorname{case} e[e'/x] \operatorname{of} nil \Rightarrow e_1[e'/x] \mid x_1::z_2 \Rightarrow e_2[z_2/x_2][e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } (x_1 \notin FV(e') \text{ or } x \notin FV(e_1)) \text{ and } x_2 \in FV(e') \text{ and } x \in FV(e_2) \text{ and } z_2 \notin FV(e') \cup FV(e_2) \cup \{x_1\} \\
\operatorname{case} e[e'/x] \operatorname{of} nil \Rightarrow e_1[e'/x] \mid z_1::z_2 \Rightarrow e_2[z_1/x_1][z_2/x_2][e'/x] &\text{if } x \neq x_1 \text{ and } x \neq x_2 \text{ and } x_1 \in FV(e') \text{ and } x \in FV(e_1) \text{ and } x_2 \in FV(e') \text{ and } x \in FV(e_2) \text{ and } z_1 \notin FV(e') \cup FV(e_2) \text{ and } z_2 \notin FV(e') \cup FV(e_2) \text{ and } z_1 \neq z_2
\end{cases}
$$

$$
(e)[e'/x] = (e[e'/x])
$$

## Imperative
$$
(\operatorname{ref}\ e)[e'/x] = \operatorname{ref}\ e[e'/x]
$$

$$
(!e)[e'/x] = !(e[e'/x])
$$

$$
(e_1 := e_2)[e/x] = e_1[e/x] := e_2[e/x]
$$

$$
()[e/x] = ()
$$

$$
(e_1;e_2)[e/x] = e_1[e/x];e_2[e/x]
$$

$$
(\operatorname{while} e_1 \operatorname{do} e_2)[e/x] = \operatorname{while} e_1[e/x] \operatorname{do} e_2[e/x]
$$

$$
(break)[e/x] = break
$$

$$
(continue)[e/x] = continue
$$

$$
<e_1, e_2>[e/x] = <e_1[e/x], e_2[e/x]>
$$
