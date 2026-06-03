class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // we are using map for lookup O(1)
        val map = mutableMapOf<Int, Int>()

        // interate through the nums array indices including the last index
        for (i in nums.indices) {
            // calculating the complement for current number from nums
            val complement = target - nums[i]

            // check if the complement key is present in the map
            if (map.containsKey(complement)) {
                // return the indices of the two complementary numbers
                return intArrayOf(map[complement]!!, i)
            }

            // if not found then add the number (key) -> index (value) in the map
            map[nums[i]] = i
        }

        // throw an exception kotlin's idiomatic way
        throw IllegalArgumentException("No two numbers make upto a target!")
    }
}
