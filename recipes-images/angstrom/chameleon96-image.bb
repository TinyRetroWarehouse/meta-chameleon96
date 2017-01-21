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
        gimp gedit midori epiphany \
	chameleon96-x11vnc \
"

export IMAGE_BASENAME = "chameleon96-image"

