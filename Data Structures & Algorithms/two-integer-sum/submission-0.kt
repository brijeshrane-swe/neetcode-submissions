class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // IntArray = primitive int[] on JVM — no boxing overhead
        // Unlike Array<Int> which wraps each value as an Integer object
        // Return type IntArray = same primitive array

        // .isEmpty() = stdlib extension function on IntArray
        // equivalent to nums.size == 0 but more idiomatic/readable
        // TC: O(1), SC: O(1)
        if (nums.isEmpty()) {
            // throw is an EXPRESSION in Kotlin (type: Nothing)
            // Nothing = this code path never returns normally
            // Compiler uses this for smart flow analysis
            throw IllegalArgumentException("Array must contain at least 2 elements!")
        }

        // .size = property access (not a method call like .length() in Java)
        // Kotlin uses properties instead of getters — nums.size not nums.getSize()
        // TC: O(1), SC: O(1)
        if (nums.size < 2) {
            throw IllegalArgumentException("Array must contain at least 2 elements!")
        }

        // mutableMapOf<Int, Int>() = stdlib factory function that creates a LinkedHashMap
        // Key   = the number value from nums
        // Value = its original index in nums
        // mutableMapOf vs mapOf: mapOf() is READ-ONLY, mutableMapOf() allows put/remove
        // <Int, Int> = generic type parameters — Kotlin infers these but explicit here for clarity
        // SC: O(n) — stores up to n key-value entries
        val seen = mutableMapOf<Int, Int>()

        // withIndex() = stdlib extension function that wraps each element as IndexedValue(index, value)
        // (index, num) = DESTRUCTURING DECLARATION — unpacks IndexedValue into two separate variables
        // equivalent to: for (i in nums.indices) { val num = nums[i] }
        // TC: O(n) — single linear pass
        for ((index, num) in nums.withIndex()) {

            // val = immutable local variable (like final in Java)
            // complement arithmetic works for negatives, zero, positives — no special case
            // e.g. target=1, num=-3 → complement = 1-(-3) = 4 
            // TC: O(1) — single subtraction, SC: O(1) — single Int on stack
            val complement = target - num

            // .containsKey() = HashMap O(1) average lookup
            // We look up BEFORE inserting to prevent self-pairing
            // e.g. nums=[3,3], target=6 → at index 0: complement=3, not in map yet → safe
            // TC: O(1) average — hash lookup
            if (seen.containsKey(complement)) {

                // seen[complement] = operator overloading for .get(complement)
                // Kotlin overloads [] operator on Map via get() operator function
                // Returns Int? (nullable) — the !! is the NON-NULL ASSERTION operator
                // !! = "I guarantee this is not null, throw NPE if wrong"
                // Safe here because containsKey() already confirmed it exists
                // intArrayOf(...) = stdlib factory, creates IntArray — like new int[]{a,b} in Java
                // TC: O(1), SC: O(1) — fixed-size result array of 2
                return intArrayOf(seen[complement]!!, index)
            }

            // seen[num] = index → operator overloading for .put(num, index)
            // [] on MutableMap is overloaded for both get and set operations
            // Stores current number and its index for future complement lookups
            // If num already exists in map, this OVERWRITES the old index
            // (safe for this problem since we only need one valid pair)
            // TC: O(1) average — hash insert, SC: O(1) per entry
            seen[num] = index
        }

        // Reached only if no valid pair exists after full traversal
        // throw as expression — type Nothing, compiler knows this never returns normally
        // TC: O(1), SC: O(1)
        throw IllegalArgumentException("No two numbers sum up to the target!")
    }
}
