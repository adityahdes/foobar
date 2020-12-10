import unittest
import solution
class FoobarTest(unittest.TestCase):
    def test_1(self):
        self.assertEqual(solution.solution("code"), "100100101010100110100010")

if __name__ == '__main__':
    unittest.main()