import sys

class bcolors:
    HEADER = '\033[95m'
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    ENDC = '\033[0m'

    def disable(self):
        self.HEADER = ''
        self.OKBLUE = ''
        self.OKGREEN = ''
        self.WARNING = ''
        self.FAIL = ''
        self.ENDC = ''

def get():
    import os
    env = os.environ
    def ioctl_GWINSZ(fd):
        try:
            import fcntl, termios, struct, os
            cr = struct.unpack('hh', fcntl.ioctl(fd, termios.TIOCGWINSZ,
        '1234'))
        except:
            return
        return cr
    cr = ioctl_GWINSZ(0) or ioctl_GWINSZ(1) or ioctl_GWINSZ(2)
    if not cr:
        try:
            fd = os.open(os.ctermid(), os.O_RDONLY)
            cr = ioctl_GWINSZ(fd)
            os.close(fd)
        except:
            pass
    if not cr:
        cr = (env.get('LINES', 25), env.get('COLUMNS', 80))

    return int(cr[1]), int(cr[0])

def clear_terminal():
    import sys
    sys.stdout.write( chr(27) + "[2J" )

def update(grid):
    clear_terminal()
    print bcolors.RED + ' GAME OF LIFE' + bcolors.ENDC
    print bcolors.YELLOW + '-'*( len(grid[0]) + 5) + bcolors.ENDC
    print
    for i, line in enumerate(grid):
        print bcolors.BLUE + '%3d ' % i + bcolors.ENDC,
        for element in line:
            if element:
                sys.stdout.write(bcolors.RED + str(element) + bcolors.ENDC)
            else:
                sys.stdout.write('0')
        print
    print bcolors.YELLOW + '-' * ( len(grid[0]) + 5 ) + bcolors.ENDC

def next(grid, new_grid):
    for x in range(0, len(grid[0])):
        for y in range(0, len(grid)):
            live_neighbors = neighbors(x, y, grid)
            if grid[y][x]:
                if live_neighbors < 2 or live_neighbors > 3:
                    new_grid[y][x] = 0
                else:
                    new_grid[y][x] = grid[y][x]
            else:
                if live_neighbors == 3:
                    new_grid[y][x] = 1

def init(grid):
    done = False
    prompt = 'CONFIG %d: Type coordinates to toggle (or start to finish) %s: '
    config_step = 0
    last_coord = ''
    while True:
        coord = []
        sys.stdout.write(prompt % (config_step, last_coord))
        while coord == []:
            cmd = raw_input()
            if cmd == 'start' or cmd == '':
                done = True
                break
            try:
                cmd = cmd.split()
                coord = [int(cmd[i]) for i in range(2)]
            except:
                sys.stdout.write(bcolors.RED + '[Invalid input] %s' %
                                 (prompt % (config_step, last_coord)) 
                                 + bcolors.ENDC)
            last_coord = str(coord)
            config_step += 1
        if done:
            break
        grid [coord[1]][coord[0]] = (grid[coord[1]][coord[0]] + 1) % 2
        update(grid)

def neighbors(x, y, grid):
	live_neighbors = 0
	for i in range(-1, 2):
		testx = (x+i) % len(grid[0])
		for j in range(-1, 2):
			testy = (y+j) % len(grid)
			if j == 0 and i == 0:
				continue
			if grid[testy][testx] == 1:
				live_neighbors += 1
	return live_neighbors

if __name__ == "__main__":
	(width, height) = get()
	grid = [(width-5)*[0] for i in range(height-5)]
	update(grid)
	init(grid)
	prompt = ('ITER %d: Type anything to continue, the number of steps to ' + 'perform (or quit to exit): ')
	iter_step = 1
	update(grid)
	while True:
		play = raw_input('%s' % (prompt % iter_step))
		if play == 'quit':
			break
		try:
			batch_steps = int(play)
		except:
			batch_steps = 1
			pass
		for i in range(batch_steps):
			new_grid = [len(grid[0])*[0] for i in range(len(grid))]
			next(grid, new_grid)
			grid, new_grid = new_grid, grid
			update(grid)
		iter_step += batch_steps
