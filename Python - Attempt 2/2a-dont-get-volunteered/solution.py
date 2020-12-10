# https://i.stack.imgur.com/5bbv3.png
def solution(src, dest):
    src_x = src % 8
    src_y = src / 8
    dest_x = dest % 8
    dest_y = dest / 8
    x = Math.abs(dest_x - src_x)
    y = Math.abs(dest_y - src_y)
    if x < y:
        t = x
        x = y
        y = x
    if x==1 and y==0:
        return 3
    if x==2 and y==2:
        return 4
    d = x - y
    if y > d:
        return d - 2 * Math.floor((d - y)/3)
    else:
        return d - 2 * Math.floor((d - y)/4)