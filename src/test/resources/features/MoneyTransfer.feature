
Feature: перевод с карты

  Scenario: перевод с своей карты на свою карту (happy path)
    Given пользователь залогинен с именем "vasya" и паролем "qwerty123"
    When пользователь переводит 5000 рублей с карты с номером "5559 0000 0000 0002" на свою 1 карту с главной страницы
    Then баланс его 1 карты из списка на главной странице должен стать 15000 рублей