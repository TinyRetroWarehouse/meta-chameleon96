require chameleon96-console-image.bb

IMAGE_INSTALL += " \
	packagegroup-xfce-base \
	xterm \
	angstrom-x11vnc-xinit \
	x11vnc \
	xserver-xorg-xvfb \
	xkbcomp \
	packagegroup-core-x11-xserver \
	xserver-nodm-init \
	xserver-common \
	gimp \
	gedit \
	epiphany \
	chameleon96-x11vnc \
"

export IMAGE_BASENAME = "chameleon96-image"

# 1/19/17
# removed midori - "Nothing RPROVIDES" error
# removed gedit - Nothing PROVIDES 'gnome-keyring' error ... Missing or unbuildable dependency chain was: ['chameleon96-image', 'gedit', 'gvfs', 'gnome-keyring']
# this is coming from meta-photography layer, so removed from bblayer.conf, added back gedit
