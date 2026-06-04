class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // IntArray = primitive int[] on JVM (no boxing overhead)
        // Unlike Array<Int> which wraps each value as Integer object
        // TC: O(1), SC: O(1)
        if (nums.size < 2) {
            // throw is an EXPRESSION in Kotlin (type: Nothing)
            // Nothing means this code path never returns normally
            throw IllegalArgumentException("Array must have at least 2 elements!")
        }

        // mapIndexed = higher-order stdlib function
        // iterates with BOTH index (i) and value (v) simultaneously
        // { i, v -> ... } is a LAMBDA — Kotlin's anonymous function syntax
        // Pair(v, i) = stdlib data class holding two values: .first and .second
        // Returns a NEW List<Pair<Int,Int>> — does NOT mutate nums
        // TC: O(n), SC: O(n) — creates n Pair objects
        val indexed = nums.mapIndexed { i, v -> Pair(v, i) }

        // sortedBy = NON-MUTATING sort — returns a new sorted list
        // { it.first } — 'it' is implicit lambda param (used when only one param)
        // .first is the sort key = the value (not the index)
        // Uses Timsort under the hood — stable, O(n log n)
        // Compare: nums.sort() mutates in place; sortedBy does NOT
        // TC: O(n log n), SC: O(n) — new sorted list
        val sorted = indexed.sortedBy { it.first }

        // Two Int variables on stack — no heap allocation
        // TC: O(1), SC: O(1)
        var start = 0
        var end = sorted.size - 1

        // Each iteration moves at least one pointer → at most n total iterations
        // TC: O(n), SC: O(1) — no extra memory inside loop
        while (start < end) {

            // .toLong() = EXPLICIT type widening — Kotlin has NO implicit promotion
            // unlike Java where int + int can silently overflow
            // e.g. Int.MAX_VALUE + 1 wraps to negative as Int, but is safe as Long
            // TC: O(1), SC: O(1) — single Long on stack
            val result = sorted[start].first.toLong() + sorted[end].first.toLong()

            // when{} without argument = clean chain of if-else branches
            // evaluated top-down, first match wins
            // 'else' is the mandatory fallback (like default in switch)
            // NO break needed — unlike Java switch
            when {

                result == target.toLong() -> {
                    // .second retrieves the ORIGINAL index stored in Pair
                    // TC: O(1), SC: O(1) — two Int vars
                    val i1 = sorted[start].second
                    val i2 = sorted[end].second

                    // minOf/maxOf = top-level stdlib functions (not methods on object)
                    // Ensures smaller index is always first — LeetCode requirement
                    // e.g. i1=4, i2=2 → returns [2, 4] not [4, 2]
                    // intArrayOf(...) = stdlib factory for IntArray, like new int[]{a,b} in Java
                    // TC: O(1), SC: O(1) — fixed size IntArray of 2
                    return intArrayOf(minOf(i1, i2), maxOf(i1, i2))
                }

                result > target -> {
                    // Sum too large → move right pointer LEFT to use a smaller value
                    // Negative numbers handled naturally — sorted order still holds
                    // e.g. sorted=[-5,-3,-1], result=-4 > -8 → end-- picks -5+(-3)=-8 
                    // TC: O(1), SC: O(1)
                    end--
                }

                else -> {
                    // Sum too small → move left pointer RIGHT to use a larger value
                    // start++ removes the most-negative (smallest) element
                    // TC: O(1), SC: O(1)
                    start++
                }
            }
        }

        // Reached only if no valid pair found
        // throw as expression — type is Nothing, compiler knows this never returns
        // TC: O(1), SC: O(1)
        throw IllegalArgumentException("No two numbers sum up to the target!")
    }
}
