# Моя реализация

Структура программы выглядит так:

```tree
src/main/kotlin/
└── com
    └── buttersus
        ├── client
        │   ├── Client.kt
        │   └── Response.kt
        ├── dsl
        │   ├── BodyMatchers.kt
        │   ├── ResponseActions.kt
        │   ├── ResponseMatchers.kt
        │   └── StatusMatchers.kt
        ├── exceptions
        │   ├── BodyResponseMatchersException.kt
        │   ├── ResponseMatchersException.kt
        │   └── StatusResponseMatchersException.kt
        └── Main.kt
```

## Некоторые замечания

Для реализации `DSL` языков Kotlin использует синтаксический сахар `trailing lambdas` - функции высокого порядка:

```kt
class A {
    val x = 0
}

object B {
    fun repeat(times: Int, code: A.() -> Unit): Unit = TODO("...")
}

fun main() {
    B.repeat(4) {
        println(x)  // x = 0
    }
}
```

Проблема в том что для каждой области видимости со своим набором методов и атрибутов требуется
объект уникального класса.

Если ещё появляется необходимость получить доступ к атрибутам из внешней области видимости,
то тут есть 3 подходящих способа реализации:

1. Передать внешний объект (_"shared state object"_) в качестве параметра основного конструктора во внутренний класс.
2. Передать атрибуты внешнего класса во внутренний с помощью основного конструктора.
3. Использовать внутренний класс.

Будем использовать второй способ с сокрытием полей для некоторых классов.
Использование внутреннего класса как мне кажется противоречит `KISS` и `SOLID`.

## Компиляция и запуск

Запустить можно при помощи команды `./gradlew run`

```sh
11:42:42 AM: Executing 'run'...

> Task :checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :processResources NO-SOURCE
> Task :compileKotlin
> Task :compileJava NO-SOURCE
> Task :classes UP-TO-DATE

> Task :run
Response(statusCode=200, body=OK)

BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 executed
11:42:44 AM: Execution finished 'run'.
```

# Задание к третьей лекции

Нужно написать свой _Domain Specific Language **(DSL)**_ для тестирования.

Есть тестируемый класс:

- ```kt
  data class Response(
      val code: Int,
      val body: String?
  )
  ```

Для этого необходимо написать возвращающий нам класс клиент,
над которым вам предстоит работать.

- ```kt
  class Client {
      fun perform(code: Int, body: String?) = ResponseActions(code, body)
  }
  ```

Теперь перейдём к `ResponseActions`.

- Она содержит в себе `response`, который содержит информацию из конструктора.
- Вам необходимо написать несколько методов для работы с этим объектом (см. ниже).
- Код в `main` должен выглядеть следующим образом:
    - ```kt
    fun main() {
        val mockClient = Client()
        val response = mockClient.perform(200, "OK")
            .andExpect {
                status {
                    isOk()
                }
                body {
                    isNotNull()
                }
            }
            .andDo { response ->
                println(response)
            }
            .response
    }
    ```

**Описание методов:**

- `fun andExpect(...): ...` : принимает 2 типа функций (`ResponseMatchers`)
    - `fun status(...): ...` : принимает 3 типа функций
        - `fun isOk(): ...` - если статус не `200`, то выбросить исключение
        - `fun isBadRequest(): ...` - если статус не `400`, то выбросить исключение
        - `fun isInternalServerError(): ...` - если статус не `500`, то выбросить исключение
    - `fun body(...): ...`
        - `fun isNull(): ...` - если body не `null`, то выбросить исключение
        - `fun isNotNull(): ...` - если body `null`, то выбросить исключение

Основываясь на описаниях, вам необходимо будет создать дополнительные классы внутри `ResponseActions`.

Подумайте, какие модификаторы вам будут необходимы.

Также реализуйте свою систему исключений `ResponseMatchersException` с двумя наследниками:

- `StatusResponseMatchersException`
- `BodyResponseMatchersException`
