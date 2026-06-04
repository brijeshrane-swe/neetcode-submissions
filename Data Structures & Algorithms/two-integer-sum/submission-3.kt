class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        // EDGE CASE: Array has fewer than 2 elements — no pair possible
        // TC: O(1) — single size check, SC: O(1) — no extra memory
        if (nums.size < 2) {
            throw IllegalArgumentException("Array must have at least 2 elements!")
        }

        // Zip each value with its original index before sorting
        // This preserves the mapping back to original positions
        // TC: O(n) — iterates once through array, SC: O(n) — creates n Pair objects
        val indexed = nums.mapIndexed { i, v -> Pair(v, i) }

        // Sort pairs by value (ascending) — works correctly for negatives, zero, positives
        // Negative numbers are handled naturally: [-5, -3, -1] is valid sorted order
        // TC: O(n log n) — comparison-based sort, SC: O(n) — new sorted list
        val sorted = indexed.sortedBy { it.first }

        // Two pointers starting at opposite ends of the sorted array
        // TC: O(1) — simple assignment, SC: O(1) — two Int variables on stack
        var start = 0
        var end = sorted.size - 1

        // Each iteration moves at least one pointer → at most n iterations total
        // TC: O(n) — linear scan, SC: O(1) — no extra memory inside loop
        while (start < end) {

            // Cast to Long BEFORE adding to avoid Int overflow
            // e.g. Int.MAX_VALUE + 1 wraps around as Int, but is safe as Long
            // TC: O(1) — two casts + one addition, SC: O(1) — single Long on stack
            val result = sorted[start].first.toLong() + sorted[end].first.toLong()

            when {
                result == target.toLong() -> {
                    // Pair found — retrieve original indices from before sorting
                    // TC: O(1) — two list accesses, SC: O(1) — two Int variables
                    val i1 = sorted[start].second
                    val i2 = sorted[end].second

                    // LeetCode requires indices in ascending order (smaller index first)
                    // e.g. i1=4, i2=2 → return [2, 4] not [4, 2]
                    // TC: O(1) — min/max comparison, SC: O(1) — fixed size IntArray of 2
                    return intArrayOf(minOf(i1, i2), maxOf(i1, i2))
                }

                result > target -> {
                    // Sum is too large → shrink from the right to bring sum down
                    // Moving end left replaces the largest element with a smaller one
                    // TC: O(1), SC: O(1)
                    end--
                }

                else -> {
                    // Sum is too small → expand from the left to bring sum up
                    // Moving start right replaces the smallest (most negative) element
                    // TC: O(1), SC: O(1)
                    start++
                }
            }
        }

        // No valid pair found after exhausting all combinations
        // Kotlin idiomatic way to signal invalid input
        // TC: O(1), SC: O(1)
        throw IllegalArgumentException("No two numbers sum up to the target!")
    }
}
