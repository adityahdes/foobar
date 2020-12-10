import unittest
import solution


class MyTestCase(unittest.TestCase):
    def test_1(self):
        self.assertEqual(1, solution.solution('1211', 10))

    def test_2(self):
        self.assertEqual(3, solution.solution('210022', 3))


if __name__ == '__main__':
    unittest.main()
