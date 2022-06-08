# Lexical
## Comments
* enclosed by pairs of `(*` and `*)`
* nestable and multi-line

## Atoms
* integer literals: `[0-9]+`
* identifiers: `[_a-z][_a-zA-Z0-9’]*`
> :warning: boolean literals are categorized as keywords

## Keywords
* `nil`
* `inl` &emsp; `inr`
* `case` &emsp; `of`
* `ref`
* `fn` &emsp; `rec`
* `let` &emsp; `in` &emsp; `end`
* `if` &emsp; `then` &emsp; `else`
* `while` &emsp; `do` &emsp; `break` &emsp; `continue`
* `true` &emsp; `false`
* `not` &emsp; `andalso` &emsp; `orelse`
> :warning: keywords cannot be bound to anything

## Operators
* `+` &emsp; `-` &emsp; `*` &emsp; `/` &emsp; `%` &emsp; `~`
* `=` &emsp; `<>` &emsp; `<` &emsp; `<=` &emsp; `>` &emsp; `>=`
* `::` &emsp; `()` &emsp; `=>`
* `:=` &emsp; `!`
* `,` &emsp; `;` &emsp; `(` &emsp; `)`
* `|`
> :warning: `not`, `andalso` and `orelse` are categorized as keywords

### Operator Precedence
| Priority | Operator | Associativity |
| ------ | ------ | ------ |
| 0 | `=>` &emsp; `\|` | right |
| 1 | `;` | left |
| 2 | `:=` | none |
| 3 | `orelse` | right |
| 4 | `andalso` | right |
| 5 | `=` &emsp; `<>` &emsp; `<` &emsp; `<=` &emsp; `>` &emsp; `>=` | none |
| 6 | `::` | right |
| 7 | `+` &emsp; `-` | left |
| 8 | `*` &emsp; `/` &emsp; `%` | left |
| 9 | (application) | left |
| 10 | `~` &emsp; `not` &emsp; `!` &emsp; `ref` &emsp; `inl` &emsp; `inr` | right |