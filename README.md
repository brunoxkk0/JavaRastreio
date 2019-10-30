# JavaRastreio
Api feita em java, para realizar o rastreio de encomendas.
Projeto não otimizado, ainda falta dar uma polida no código, porem tudo funciona.

Para utilizar o projeto, crie um novo objeto do tipo `RastreioAPI`, passe o SRO como argumento e seja feliz.

Métodos disponíveis na classe:
 `public String getCurrentStatus()` * Retorna o "local" atual da encomenda.
 
 `public String getEvents()` * Retorna uma array em formato json com todos os eventos da encomenda.
 
 `public String getSro()` * Retorna o Sro usado para a consulta.
