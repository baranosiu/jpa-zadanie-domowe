# Zadanie domowe z JPA

Chodzi o klasę abstrakcyjną `EntityRepository` - jak w linii 52. można się dobrać do nazwy tabeli w jakiej są przechowywane `Entity` bez przekazywania tego "ręcznie" w konstruktorze przez klasy pochodne?

W tej chwili w klasach Entity zdefiniowałem pole statyczne, które jest przekazywana zarówno do adnotacji jak i do konstruktora repozytorium. Czy da się tę informację jakoś wyłuskać tylko na podstawie klasy powiedzmy `Buyer.class` (przy tworzeniu repozytorium może jeszcze nie być obiektów danego typu) za pomocą silnika Hibernate?
