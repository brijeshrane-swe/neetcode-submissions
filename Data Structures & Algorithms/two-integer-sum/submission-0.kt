class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // TC: O(1) — HashMap initialisation, SC: O(n) — stores up to n entries
        val seen = mutableMapOf<Int, Int>()

        // TC: O(n) — single pass through array
        for ((index, num) in nums.withIndex()) {

            // Calculate what value we need to complete the pair
            // TC: O(1) — arithmetic op, SC: O(1) — single Int
            val complement = target - num

            // TC: O(1) average — HashMap lookup
            if (seen.containsKey(complement)) {
                // Both indices found — return immediately
                // TC: O(1), SC: O(1)
                return intArrayOf(seen[complement]!!, index)
            }

            // Store current number and its index for future lookups
            // TC: O(1) average — HashMap insert, SC: O(1) per entry
            seen[num] = index
        }

        // Kotlin idiomatic exception — only reached if no pair exists
        throw IllegalArgumentException("No two numbers sum up to target!")
    }
}
