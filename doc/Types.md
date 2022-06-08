# Types
## Simply-typed Lambda Calculus
$$
\begin{aligned}
t &::= \quad bool &\text{(boolean type)} \\
 & & \\
 &| \qquad int &\text{(integer number type)} \\
 & & \\
 &| \qquad t_1 \rightarrow t_2 &\text{(arrow type)}
\end{aligned}
$$

---
$$
\begin{aligned}
\alpha &::= \quad bool &\text{(boolean type)} \\
 & & \\
 &| \qquad int &\text{(integer number type)}
\end{aligned}
$$

## Extensions
$$
\begin{aligned}
t &::= \quad \ldots & \\
 &| \qquad t_1 \times t_2 &\text{(pair type)} \\
 & & \\
 &| \qquad t_1 + t_2 &\text{(sum type)} \\
 & & \\
 &| \qquad t\ \operatorname{list} &\text{(list type)}
\end{aligned}
$$

---
$$
\begin{aligned}
\alpha &::= \quad \ldots & \\
 &| \qquad \alpha_1 \times \alpha_2 &\text{(pair type)} \\
 & & \\
 &| \qquad \alpha_1 + \alpha_2 &\text{(sum type)} \\
 & & \\
 &| \qquad \alpha\ \operatorname{list} &\text{(list type)}
\end{aligned}
$$

## Imperative
$$
\begin{aligned}
t &::= \quad \ldots & \\
 &| \qquad t\ \operatorname{ref} &\text{(reference type)} \\
 &| \qquad unit &\text{(unit type)}
\end{aligned}
$$

---
$$
\begin{aligned}
\alpha &::= \quad \ldots & \\
 &| \qquad t\ \operatorname{ref} &\text{(reference type)}
\end{aligned}
$$
