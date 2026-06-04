class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        // EDGE CASE 1: Empty array — no pairs possible
        // TC: O(1), SC: O(1)
        if (nums.isEmpty()) {
            throw IllegalArgumentException("Array must contain at least 2 elements!")
        }

        // EDGE CASE 2: Single element — can't form a pair
        // TC: O(1), SC: O(1)
        if (nums.size < 2) {
            throw IllegalArgumentException("Array must contain at least 2 elements!")
        }

        // SC: O(n) — HashMap stores up to n key-value pairs
        val seen = mutableMapOf<Int, Int>()

        // TC: O(n) — single linear pass through the array
        for ((index, num) in nums.withIndex()) {

            // Works for negatives, zero, and positives — no special case needed
            // EDGE CASE 3: target - num handles negative numbers naturally
            // e.g. target=1, num=-3 → complement=4
            // TC: O(1) — arithmetic, SC: O(1) — single Int on stack
            val complement = target - num

            // EDGE CASE 4: Duplicate values like [3, 3], target=6
            // Safe because we look up BEFORE inserting — avoids self-pairing
            // TC: O(1) average — HashMap lookup
            if (seen.containsKey(complement)) {
                // TC: O(1), SC: O(1) — fixed-size result array
                return intArrayOf(seen[complement]!!, index)
            }

            // EDGE CASE 5: Overflow — target - num is safer than nums[i] + nums[j]
            // For Int.MAX_VALUE inputs, subtraction stays in range more reliably
            // than addition. Use Long if extreme overflow is a concern:
            // val complement = target.toLong() - num.toLong()
            // TC: O(1) average — HashMap insert
            seen[num] = index
        }

        // EDGE CASE 6: No valid pair found — Kotlin idiomatic exception
        // TC: O(1), SC: O(1)
        throw IllegalArgumentException("No two numbers sum up to the target!")
    }
}
