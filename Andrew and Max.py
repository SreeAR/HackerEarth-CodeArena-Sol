t = int(input())
for tt in range(t):
    x,y,z = map(int,input().split())
    a = (x-y+z)/2
    b = (y-z+x)/2
    c = (z-x+y)/2
    area = 2*a*b+2*b*c+2*c*a
    print("{0:.2f}".format(area))