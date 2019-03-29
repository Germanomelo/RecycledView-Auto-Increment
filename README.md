# RecyclerView-Auto-Increment
Um recyclerView que auto incrementa elementos ao chegar no final da lista.

## Pra que Serve?
Quando se tem um banco de dados grande e as consultas estão lentas, a melhor solução é paginar seus dados buscando aos poucos, porém fazer paginação na interface do Android para que o usuário faça manualmente não é muito elegante nem dinâmico, então a solução serve para quando o usuário visualizar os últimos elementos atuais do recyclerView ela se auto incremente com uma nova consulta ao banco.

## Demonstração
Na demonstração as paginas foram configuradas com 30 itens, e o gatilho é a visualização do item final(30) menos 5, que faz o incremento quando o item 25 é visualizado, a sequencia de incremento é 25, 55, 85... até que os itens acabem.

![videotogif_2019 03 29_09 10 47](https://user-images.githubusercontent.com/17008397/55232537-a0a49c00-5204-11e9-85a0-551e49fae93d.gif)
