class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        if (nums.size < 2) {
            throw IllegalArgumentException("Array must have at least 2 elements!")
        }

        // Preserve original indices before sorting
        // SC: O(n)
        val indexed = nums.mapIndexed { i, v -> Pair(v, i) }

        // TC: O(n log n), SC: O(n)
        val sorted = indexed.sortedBy { it.first }

        var start = 0
        var end = sorted.size - 1

        // TC: O(n)
        while (start < end) {

            // Overflow-safe addition using Long
            val result = sorted[start].first.toLong() + sorted[end].first.toLong()

            when {
                result == target.toLong() -> {
                    val i1 = sorted[start].second
                    val i2 = sorted[end].second
                    // FIX: always return [smallerIndex, largerIndex]
                    return intArrayOf(minOf(i1, i2), maxOf(i1, i2))
                }
                result > target -> end--
                else -> start++
            }
        }

        throw IllegalArgumentException("No two numbers sum up to the target!")
    }
}