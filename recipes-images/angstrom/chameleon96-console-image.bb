require recipes-images/angstrom/console-image.bb

IMAGE_INSTALL += " \
	bash \
	chameleon96-fpga-init \
	chameleon96-lighttpd-conf \
	chameleon96-linux-firmware \
	chameleon96-usb-gadget \
	chameleon96-webcontent \
	chameleon96-x11vnc-init \
	chameleon96-xfce-init \
	ethtool \
	gcc \
	gdb \
	gdbserver \
	gnuplot \
	i2c-tools \
	iperf \
	iw \
	kernel-dev \
	kernel-image \
	kernel-modules \
	lighttpd \
	lighttpd-module-cgi \
	net-tools \
	nfs-utils-client \
	packagegroup-sdk-target \
	python \
	tar \
	usbutils \
	vim \
	vim-vimrc \
"
export IMAGE_BASENAME = "chameleon96-console-image"

#overload timestamp function in image.bbclass

rootfs_update_timestamp () {
        date -u +%4Y%2m%2d%2H%2M -d "+1 day">${IMAGE_ROOTFS}/etc/timestamp
}

EXPORT_FUNCTIONS rootfs_update_timestamp

# add later
#	chameleon96-fpga-leds \
