# Домашнее задание 2. Реверс

**1. Разработайте класс `Reverse`, читающий числа из стандартного ввода, и выводящий их на стандартный вывод в обратном порядке.**

**2. В каждой строке входа содержится некоторое количество целых чисел (может быть 0). Числа разделены пробелами. Каждое число помещается в тип `int`.**

**3. Порядок строк в выходе должен быть обратным по сравнению с порядком строк во входе. Порядок чисел в каждой строке также должен быть обратным к порядку чисел во входе.**

**4. Вход содержит не более 10<sup>6</sup> чисел и строк.**

**5. Для чтения чисел используйте класс [Scanner](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Scanner.html).**

**6. Примеры работы программы:**

* Ввод:

      1 2
      3
* Вывод:

      3
      2 1

___

* Ввод:

      3
      2 1
* Вывод:

      1 2
      3

___

* Ввод:

      1

      2 -3
* Вывод:

      -3 2

      1

___

* Ввод:

      1     2
      3     4
* Вывод:

      4 3
      2 1

___

### Модификация

**Min**

* Рассмотрим входные данные как (не полностью определенную) матрицу, вместо каждого числа выведите минимум из чисел на текущей строке и колонке.
* Класс должен иметь имя ReverseMin
