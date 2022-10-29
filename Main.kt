import java.util.*
import java.util.Stack


val INPUT = """
(a+[b*c]-{d/3})
(a + [b * c) - 17]
(((a * x) + [b] * y) + c
auf(zlo)men [gy<psy>] four{s}
"""

fun getStringStack(input: String): List<String> {
    var strStack : List<String>
    strStack = input.lines()
    return strStack
}

fun getBracketStack(input: String): Stack<Char> {
    var bracketStack = Stack<Char>()
    
    
    //get stack of brackets
    for (b in input) {
        if (b.toString().equals("(") || b.toString().equals(")") || b.toString().equals("[") || b.toString().equals("]") || b.toString().equals("{") || b.toString().equals("}") || b.toString().equals("<") || b.toString().equals(">")) {
        	bracketStack.add(b.toChar())
        }
    }
    return bracketStack
}

fun isMatching(bStack: Stack<Char>): Boolean {
    val checkStack = Stack<Char>()
    
    
//brackets to check for
    val map = mapOf(
        '}' to '{',
        ')' to '(',
        ']' to '[',
        '>' to '<'
    )
    
    //inspiration from https://dev.to/rkowase/leetcode-in-kotlin-20-valid-parentheses-3g7h for traversing through bracket stack elements and conditions of elements for new stack 
    bStack.forEach {
        //adding element to new stack from bracket stack
        checkStack.push(it)
		
        //no matching bracket left in stack
        if (map.containsKey(it)) {
            if (checkStack.size < 2) {
                return false
            }
            //check if 2nd previous matching it
            if (checkStack[checkStack.size - 2] != map[it]) {
                return false
            }
            //delete last 2 elements
            checkStack.pop()
            checkStack.pop()
        }
    }
     return checkStack.isEmpty()
}

fun checkBrackets(input: String): String {
    var answer = ""
    
    var strings = getStringStack(input)
    var count = strings.count()
    
    //remove top and bottom empty lines, only need to subtract one because counter starts at 1
    count = count - 1
    
    var counter = 1
	do {
    	var bStack = getBracketStack(strings.get(counter))
        if (isMatching(bStack) == false) {
            answer = answer.plus("0 ")
        }
        if (isMatching(bStack) == true) {
            answer = answer.plus("1 ")
        }
        counter++
	} while (counter < count)
    
    
	return answer
}


fun main() {
    print(checkBrackets(INPUT))
}
