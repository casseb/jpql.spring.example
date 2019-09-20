# Testes de Performance utilizando JPQL

Ao realizar testes recebi o seguinte retorno:

~~~
2019-09-19 20:40:21.454  INFO 25798 --- [           main] com.bycasseb.jpql.JPQLTests              : 43:796000000  : Implícito
2019-09-19 20:40:21.455  INFO 25798 --- [           main] com.bycasseb.jpql.JPQLTests              : 40:113000000  : Explícito
2019-09-19 20:40:21.455  INFO 25798 --- [           main] com.bycasseb.jpql.JPQLTests              : 40:153000000  : Implícito Complexo
2019-09-19 20:40:21.455  INFO 25798 --- [           main] com.bycasseb.jpql.JPQLTests              : 46:65000000  : Explícito Complexo
~~~

~~~
2019-09-19 20:50:09.127  INFO 29646 --- [           main] com.bycasseb.jpql.JPQLTests              : 38:944000000  : Implícito
2019-09-19 20:50:09.127  INFO 29646 --- [           main] com.bycasseb.jpql.JPQLTests              : 36:640000000  : Explícito
2019-09-19 20:50:09.127  INFO 29646 --- [           main] com.bycasseb.jpql.JPQLTests              : 35:535000000  : Implícito Complexo
2019-09-19 20:50:09.127  INFO 29646 --- [           main] com.bycasseb.jpql.JPQLTests              : 39:798000000  : Explícito Complexo
~~~

Não foi possível observar uma mudança muito significativa, aparentemente em SQLs simples é mais performático deixar o join explícito, enquanto que em SQLs mais complexos convêm deixar implícito. 
