# Полуфабрикат проекта с Keycloak

## Что это?
Заготовка для веб-приложений с простой авторизацией USERNAME-PASSWORD

## Как использовать?
1) Поднять контейнер с keycloak
2) Импортировать `realm-export.json` или создать свою конфигурацию Realm'а
3) Запустить Spring Boot приложение и идти на localhost:8081/login

## API
- `/login` - страница логина от Keycloak, там же можно регистрироваться
- `/logout` - страница выхода от Keycloak, перенаправляет на `/logged-out`
- `/logged-out` - доступная всем страница
- `/whoami` - страница проверки входа, выводит имя пользователя и почту

## Как настроить свой Realm с нуля?
1) Создаем новый Realm и запоминаем его имя
2) Переходим в `Realm Settings/Login` и включаем `User registration` для кнопки регистрации на странице логина
3) Переходим в `Clients/Create` и создаем новый клиент с протоколом `openid-connect`
4) Запоминаем ID нашего клиента и его ключ в `Clients/{client_id}/Credentials`
5) Убеждаемся, что у клиента включен `Standard Flow Enabled` и `Direct Access Grants Enabled` в `Clients/{client_id}/Settings`
6) Идем в `application.properties` и `SecurityConfiguration.java` и меняем там все данные на наши