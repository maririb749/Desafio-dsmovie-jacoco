<h1 align="center"> Desafio DsMovie Jacoco </h1>

## Sobre o projeto:
<div align= "justify">

Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count).  

###

## Modelo conceitual:

![img_ds_movie](https://github.com/maririb749/Desafio-dsmovie-jacoco/assets/85500087/f5feb895-1ebe-4255-a82c-a38a42b10ce3)

###

<div align= "justify">
O desafio consiste em implantar os testes unitários com JUnit e Mockito em um projeto SpringBoot com Java. Os testes estão logo abaixo. O relatório do Jacoco deve reportar 100% de cobertura.

</div> 

###

## MovieServiceTests:

 <ul>
  <ul>
    <li>findAllShouldReturnPagedMovieDTO</li>
    <li>findByIdShouldReturnMovieDTOWhenIdExists</li>
    <li>findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
    <li>insertShouldReturnMovieDTO</li>
    <li>updateShouldReturnMovieDTOWhenIdExists</li>
    <li>updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
    <li>deleteShouldDoNothingWhenIdExists</li>
    <li>deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
    <li>deleteShouldThrowDatabaseExceptionWhenDependentId</li>
 </ul>
</ul>

###
## ScoreServiceTests:

 <ul>
  <ul>
    <li>saveScoreShouldReturnMovieDTO</li>
    <li>saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId</li>
  </ul>
</ul>

###
## UserServiceTests:

 <ul>
  <ul>
    <li>authenticatedShouldReturnUserEntityWhenUserExists</li>
    <li>authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</li>
     <li>loadUserByUsernameShouldReturnUserDetailsWhenUserExists</li>
    <li>loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</li>
  </ul>
</ul>
