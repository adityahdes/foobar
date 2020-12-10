from fractions import Fraction


def answer(pegs):
    arr_length = len(pegs)
    if arr_length <= 1:
        return [-1, -1]

    even = True if (arr_length % 2 == 0) else False
    peg_sum = (- pegs[0] + pegs[arr_length - 1]) if even else (- pegs[0] - pegs[arr_length - 1])

    if arr_length > 2:
        for index in range(1, arr_length - 1):
            peg_sum += 2 * (-1) ** (index + 1) * pegs[index]

    first_gear_radius = Fraction(2 * (float(peg_sum) / 3 if even else peg_sum)).limit_denominator()

    current_radius = first_gear_radius
    for index in range(0, arr_length - 2):
        center_distance = pegs[index + 1] - pegs[index]
        next_radius = center_distance - current_radius
        if current_radius < 1 or next_radius < 1:
            return [-1, -1]
        else:
            current_radius = next_radius

    return [first_gear_radius.numerator, first_gear_radius.denominator]
