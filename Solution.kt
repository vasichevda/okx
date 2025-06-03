//1 - solution with stacks
fun decodeString(s: String): String {
    val countStack = ArrayDeque<Int>()
    val stringStack = ArrayDeque<StringBuilder>()
    var current = StringBuilder()
    var k = 0

    for (char in s) {
        when {
            char.isDigit() -> {
                k = k * 10 + (char - '0')
            }
            char == '[' -> {
                countStack.addLast(k)
                stringStack.addLast(current)
                current = StringBuilder()
                k = 0
            }
            char == ']' -> {
                val repeat = countStack.removeLast()
                val prev = stringStack.removeLast()
                repeat(repeat) {
                    prev.append(current)
                }
                current = prev
            }
            else -> {
                current.append(char)
            }
        }
    }

    return current.toString()
}

//2 - solution with recursion
fun decodeString(s: String): String {
    var index = 0

    fun decode(): String {
        val result = StringBuilder()
        var k = 0

        while (index < s.length) {
            val ch = s[index]
            when {
                ch.isDigit() -> {
                    k = k * 10 + (ch - '0')
                    index++
                }
                ch == '[' -> {
                    index++
                    val decoded = decode()
                    repeat(k) {
                        result.append(decoded)
                    }
                    k = 0
                }
                ch == ']' -> {
                    index++
                    return result.toString()
                }
                else -> {
                    result.append(ch)
                    index++
                }
            }
        }

        return result.toString()
    }

    return decode()
}

//3 - solution with lists and cycle
fun decodeString(s: String): String {
    val countList = mutableListOf<Int>()
    val stringList = mutableListOf<StringBuilder>()
    var current = StringBuilder()
    var k = 0

    for (char in s) {
        when {
            char.isDigit() -> {
                k = k * 10 + (char - '0')
            }
            char == '[' -> {
                countList.add(k)
                stringList.add(current)
                current = StringBuilder()
                k = 0
            }
            char == ']' -> {
                val repeat = countList.removeLast()
                val prev = stringList.removeLast()
                repeat(repeat) {
                    prev.append(current)
                }
                current = prev
            }
            else -> {
                current.append(char)
            }
        }
    }

    return current.toString()
}

//k calculate logic ASCII/Unicode 
//k = 0 * 10 + ('2' - '0') = 2
//k = 2 * 10 + ('3' - '0') = 23
