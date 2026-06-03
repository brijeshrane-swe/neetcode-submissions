class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()

        for (i in 0..nums.size - 1) {
            val complement = target - nums[i]

            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }

            map[nums[i]] = i
        }

        throw IllegalArgumentException("No two numbers make upto a target!")
    }
}
