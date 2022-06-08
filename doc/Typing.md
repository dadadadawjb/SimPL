# Typing
## Simply-typed Lambda Calculus
$$
\frac{x:t \in \Gamma}{\Gamma \vdash x:t, \{\}}(CT-Name)
$$

$$
\frac{\Gamma, x:t_1 \vdash e:t_2, q}{\Gamma \vdash \operatorname{fn} x \Rightarrow e : t_1 \rightarrow t_2, q}(CT-Fn)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1\ e_2:t, q_1 \cup q_2 \cup \{t_1 = t_2 \rightarrow t\}}(CT-App)
$$

$$
\frac{}{\Gamma \vdash true:bool, \{\}}(CT-True)
$$

$$
\frac{}{\Gamma \vdash false:bool, \{\}}(CT-False)
$$

$$
\frac{\Gamma \vdash e:t, q}{\Gamma \vdash \operatorname{not} e : bool, q \cup \{t=bool\}}(CT-Not)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1 \operatorname{andalso} e_2 : bool, q_1 \cup q_2 \cup \{t_1=bool, t_2=bool\}}(CT-AndAlso)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1 \operatorname{orelse} e_2 : bool, q_1 \cup q_2 \cup \{t_1=bool, t_2=bool\}}(CT-OrElse)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2 \qquad \Gamma \vdash e_3:t_3, q_3}{\Gamma \vdash \operatorname{if} e_1 \operatorname{then} e_2 \operatorname{else} e_3 : t_2, q_1 \cup q_2 \cup q_3 \cup \{t_1 = bool, t_2 = t_3\}}(CT-Cond)
$$

$$
\frac{}{\Gamma \vdash n:int, \{\}}(CT-Int)
$$

$$
\frac{\Gamma \vdash e:t, q}{\Gamma \vdash \sim e : int, q \cup \{t=int\}}(CT-Neg)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2 \qquad \operatorname{arithop} \in \{+, -, *, /, \%\}}{\Gamma \vdash e_1 \operatorname{arithop} e_2 : int, q_1 \cup q_2 \cup \{t_1=int, t_2=int\}}(CT-Arith)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2 \qquad \operatorname{relop} \in \{<, <=, >, >=\}}{\Gamma \vdash e_1 \operatorname{relop} e_2 : bool, q_1 \cup q_2 \cup \{t_1=int, t_2=int\}}(CT-Rel)
$$

$$
\frac{\Gamma \vdash e_1:\alpha_1, q_1 \qquad \Gamma \vdash e_2:\alpha_2, q_2 \qquad \operatorname{eqop} \in \{=, <>\}}{\Gamma \vdash e_1 \operatorname{eqop} e_2 : bool, q_1 \cup q_2 \cup \{\alpha_1=\alpha_2\}}(CT-Eq)
$$

## Extensions
$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma, x:t_1 \vdash e_2:t_2, q_2}{\Gamma \vdash \operatorname{let} x=e_1 \operatorname{in} e_2 \operatorname{end} : t_2, q_1 \cup q_2}(CT-Let)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash (e_1, e_2) : t_1 \times t_2, q_1 \cup q_2}(CT-Pair)
$$

$$
\frac{\Gamma \vdash e:t_1, q}{\Gamma \vdash \operatorname{inl} e:t_1+t_2, q}(CT-SumInl)
$$

$$
\frac{\Gamma \vdash e:t_2, q}{\Gamma \vdash \operatorname{inr} e:t_1+t_2, q}(CT-SumInr)
$$

$$
\frac{\Gamma \vdash e:t_0, q_0 \qquad \Gamma, x_1:t_1 \vdash e_1:t'_1, q_1 \qquad \Gamma, x_2:t_2 \vdash e_2:t'_2, q_2}{\Gamma \vdash \operatorname{case} e \operatorname{of} \operatorname{inl} x_1 \Rightarrow e_1 \mid \operatorname{inr} x_2 \Rightarrow e_2 : t'_1, q_0 \cup q_1 \cup q_2 \cup \{t_0 = t_1+t_2, t'_1 = t'_2\}}(CT-CaseSum)
$$

$$
\frac{\Gamma, x:t_1 \vdash e:t_2, q}{\Gamma \vdash \operatorname{rec} x \Rightarrow e : t_2, q \cup \{t_1=t_2\}}(CT-Rec)
$$

$$
\frac{}{\Gamma \vdash nil:\alpha\ \operatorname{list}, \{\}}(CT-Nil)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1::e_2 : t_2, q_1 \cup q_2 \cup \{t_2=t_1\ \operatorname{list}\}}(CT-Cons)
$$

$$
\frac{\Gamma \vdash e:t_0, q_0 \qquad \Gamma \vdash e_1:t'_1, q_1 \qquad \Gamma, x_1:t_1, x_2:t_1\ \operatorname{list} \vdash e_2:t'_2, q_2}{\Gamma \vdash \operatorname{case} e \operatorname{of} nil \Rightarrow e_1 \mid x_1::x_2 \Rightarrow e_2 : t'_1, q_0 \cup q_1 \cup q_2 \cup \{t_0=t_1\ \operatorname{list}, t'_1=t'_2\}}(CT-CaseList)
$$

$$
\frac{\Gamma \vdash e:t, q}{\Gamma \vdash (e):t, q}(CT-Group)
$$

## Imperative
$$
\frac{\Gamma \vdash e:t, q}{\Gamma \vdash \operatorname{ref}\ e : t\ \operatorname{ref}, q}(CT-Ref)
$$

$$
\frac{\Gamma \vdash e:t, q}{\Gamma \vdash !e:t_1, q \cup \{t=t_1\ \operatorname{ref}\}}(CT-Deref)
$$

$$
\frac{\Gamma \vdash e_1 : t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1 := e_2 : unit, q_1 \cup q_2 \cup \{t_1=t_2\ \operatorname{ref}\}}(CT-Assign)
$$

$$
\frac{}{\Gamma \vdash ():unit, \{\}}(CT-Unit)
$$

$$
\frac{\Gamma \vdash e_1 : t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash e_1;e_2 : t_2, q_1 \cup q_2 \cup \{t_1=unit\}}(CT-Seq)
$$

$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2:t_2, q_2}{\Gamma \vdash \operatorname{while} e_1 \operatorname{do} e_2 : unit, q_1 \cup q_2 \cup \{t_1=bool, t_2=unit\}}(CT-Loop)
$$

$$
\frac{}{\Gamma \vdash break : unit, \{\}}(CT-Break)
$$

$$
\frac{}{\Gamma \vdash continue : unit, \{\}}(CT-Continue)
$$

## Polymorphism
$$
\frac{\Gamma \vdash e_1:t_1, q_1 \qquad \Gamma \vdash e_2[e_1/x]:t_2, q_2}{\Gamma \vdash \operatorname{let} x=e_1 \operatorname{in} e_2 \operatorname{end} : t_2, q_1 \cup q_2}(CT-LetPoly1)
$$

$$
\frac{\Gamma \vdash e_1:t'_1, q_1 \qquad \Gamma, x:\forall t_1 \ldots t_n . t'_1 \vdash e_2:t'_2, q_2}{\Gamma \vdash \operatorname{let} x=e_1 \operatorname{in} e_2 \operatorname{end} : t'_2, q_1 \cup q_2}(CT-LetPoly2)
$$
