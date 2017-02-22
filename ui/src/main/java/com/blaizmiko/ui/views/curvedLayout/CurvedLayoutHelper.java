package com.blaizmiko.ui.views.curvedLayout;

import android.graphics.Path;

final class CurvedLayoutHelper {

    interface Gravity {
        int TOP = 0;
        int BOTTOM = 1;
    }

    interface Direction {
        int OUTWARD = 0;
        int INWARD = 1;
    }

    static Path getOutlinePath(final int width,
                               final int height,
                               final int curvatureHeight,
                               final int direction,
                               final int gravity,
                               final int paddingTop,
                               final int paddingBottom,
                               final int paddingLeft,
                               final int paddingRight) {

        final Path path = new Path();

        if (direction == Direction.OUTWARD) {
            if (gravity == Gravity.TOP) {
                path.moveTo(paddingLeft, paddingTop);
                path.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
                path.cubicTo(paddingLeft, height - curvatureHeight - paddingBottom,
                        width / 2 - paddingRight, height - paddingBottom,
                        width - paddingRight, height - curvatureHeight - paddingBottom);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(paddingLeft, paddingTop);
                path.close();
            } else {
                path.moveTo(0, height);
                path.moveTo(0, curvatureHeight);
                path.cubicTo(0, curvatureHeight, width / 2, 0, width, curvatureHeight);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            }
        } else {
            if (gravity == Gravity.TOP) {
                path.moveTo(paddingLeft, paddingTop);
                path.lineTo(paddingLeft, height - paddingBottom);
                path.cubicTo(paddingLeft, height - paddingBottom,
                        width / 2 - paddingRight, height - curvatureHeight - paddingBottom,
                        width - paddingRight, height - paddingBottom);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(paddingLeft, paddingTop);
                path.close();
            } else {
                path.moveTo(0, height);
                path.lineTo(0, 0);
                path.cubicTo(0, 0, width / 2, curvatureHeight, width, curvatureHeight);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            }
        }

        return path;
    }

    static Path getClipPath(final int width,
                            final int height,
                            final int curvatureHeight,
                            final int direction,
                            final int gravity,
                            final int paddingTop,
                            final int paddingBottom,
                            final int paddingLeft,
                            final int paddingRight) {

        final Path path = new Path();

        if (direction == Direction.OUTWARD) {
            if (gravity == Gravity.TOP) {
                path.moveTo(0, 0);
                path.lineTo(paddingLeft, paddingTop);
                path.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
                path.cubicTo(paddingLeft, height - curvatureHeight - paddingBottom,
                        width / 2 - paddingRight, height - paddingBottom,
                        width - paddingRight, height - curvatureHeight - paddingBottom);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(paddingLeft, paddingTop);
                path.lineTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, curvatureHeight);
                path.cubicTo(width, curvatureHeight, width / 2, 0, 0, curvatureHeight);
                path.lineTo(0, 0);
                path.close();
            }
        } else {
            if (gravity == Gravity.TOP) {
                path.moveTo(0, 0);
                path.lineTo(paddingLeft, paddingTop);
                path.lineTo(paddingLeft, height - paddingBottom);
                path.cubicTo(paddingLeft, height - paddingBottom,
                        width / 2 - paddingRight, height - curvatureHeight - paddingBottom,
                        width - paddingRight, height - paddingBottom);
                path.lineTo(width - paddingRight, paddingTop);
                path.lineTo(paddingLeft, paddingTop);
                path.lineTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.cubicTo(width, 0, width / 2, curvatureHeight, 0, 0);
                path.lineTo(0, 0);
                path.close();
            }
        }

        return path;
    }
}
