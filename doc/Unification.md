# Unification
## Simply-typed Lambda Calculus
$$
\frac{}{(S, \{a = a\} \cup q) \rightarrow (S, q)}
$$

$$
\frac{a \notin TV(s)}{(S, \{a = s\} \cup q) \rightarrow ([a = s] \circ S, q[s/a])}
$$

$$
\frac{a \notin TV(s)}{(S, \{s = a\} \cup q) \rightarrow ([a = s] \circ S, q[s/a])}
$$

$$
\frac{}{(S, \{bool=bool\} \cup q) \rightarrow (S, q)}
$$

$$
\frac{}{(S, \{int=int\} \cup q) \rightarrow (S, q)}
$$

$$
\frac{}{(S, \{s_{11} \rightarrow s_{12} = s_{21} \rightarrow s_{22}\} \cup q) \rightarrow (S, \{s_{11} = s_{21}, s_{12} = s_{22}\} \cup q)}
$$

## Extensions
$$
\frac{}{(S, \{s_{11} \times s_{12} = s_{21} \times s_{22}\} \cup q) \rightarrow (S, \{s_{11} = s_{21}, s_{12} = s_{22}\} \cup q)}
$$

$$
\frac{}{(S, \{s_{11} + s_{12} = s_{21} + s_{22}\} \cup q) \rightarrow (S, \{s_{11} = s_{21}, s_{12} = s_{22}\} \cup q)}
$$

$$
\frac{}{(S, \{s_1 \operatorname{list} = s_2 \operatorname{list}\} \cup q) \rightarrow (S, \{s_1 = s_2\} \cup q)}
$$

## Imperative
$$
\frac{}{(S, \{s_1 \operatorname{ref} = s_2 \operatorname{ref}\} \cup q) \rightarrow (S, \{s_1 = s_2\} \cup q)}
$$

$$
\frac{}{(S, \{unit = unit\} \cup q) \rightarrow (S, q)}
$$
