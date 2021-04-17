# Домашнее задание 4. Вычисление в различных типах
## Добавьте в программу разбирающую и вычисляющую выражения трех переменных поддержку вычисления в различных типах.

  **1. Создайте класс** `expression.generic.GenericTabulator`**, реализующий интерфейс** `expression.generic.Tabulator`**:**

        public interface Tabulator {
           Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception;
        }

#### Аргументы

  1) `mode` — режим работы
  
   **Режим	Тип**
   
        i    //int с детекцией переполнений
        d    //double
        bi   //BigInteger
        
  2) `expression` — вычисляемое выражение;
  
  3) `x1, x2; y1, y2; z1, z2` — диапазоны изменения переменны (включительно).
  
  4) Возвращаемое значение — таблица значений функции, где `R[i][j][k]` соответствует `x = x1 + i, y = y1 + j, z = z1 + k`. Если вычисление завершилось ошибкой, в соответствующей ячейке должен быть `null`.
  
  **2. Доработайте интерфейс командной строки:**
  
  1) Первым аргументом командной строки программа должна принимать указание на тип, в котором будут производится вычисления:
    
   **Опция	Тип**
            
        -i    //int с детекцией переполнений
        -d    //double
        -bi   //BigInteger
    
  2) Вторым аргументом командной строки программа должна принимать выражение для вычисления.
  
  3) Программа должна выводить результаты вычисления для всех целочисленных значений переменных из диапазона `−2..2`.
  
  **3. Реализация не должна содержать [непроверяемых преобразований типов.](https://docs.oracle.com/javase/specs/jls/se11/html/jls-5.html#jls-5.1.9)**
  
  **4. Реализация не должна использовать аннотацию [@SuppressWarnings.](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.6.4.5)**
  
  **5. При выполнении задания следует обратить внимание на простоту добавления новых типов и операциий.**

## Дополнительно реализовать поддержку режимов:

        u   //int без проверки на переполнение
        l   //long без проверки на переполнение
        s   //short без проверки на переполнение