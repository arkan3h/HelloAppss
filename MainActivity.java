

        ImageButton eyedrop = (ImageButton) findViewById(R.id.eyedrop);
        eyedrop.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View view) {
                eyedrop.setVisibility(View.INVISIBLE);
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.eye_drop, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(view, Gravity.END | Gravity.BOTTOM, 0, 0);
                popupWindow.setAnimationStyle(R.style.Animation);

                PhotoView pimageview = findViewById(R.id.imageView);
                TextView prgb = popupView.findViewById(R.id.rgb);
                TextView phex = popupView.findViewById(R.id.hex);

                ImageButton pcolor = (ImageButton) popupView.findViewById(R.id.eyedrop_close);

                pimageview.setDrawingCacheEnabled(true);
                pimageview.buildDrawingCache(true);

                pimageview.setOnTouchListener(new View.OnTouchListener() {
                    @SuppressLint("ClickableViewAccessibility")
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                            Bitmap bitmap = pimageview.getDrawingCache();

                            int pixel = bitmap.getPixel((int)event.getX(), (int)event.getY());

                            int r = Color.red(pixel);
                            int g = Color.green(pixel);
                            int b = Color.blue(pixel);

                            String hex = Integer.toHexString(pixel);

                            pcolor.setBackgroundColor(Color.rgb(r,g,b));

                            phex.setText("Hex : #" + hex);
                            prgb.setText("R : " + r + "\nG : " + g + "\nB : " + b );
                        }
                        return true;
                    }
                });

                pcolor.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        eyedrop.setVisibility(View.VISIBLE);
                    }
                });

                popupWindow.showAsDropDown(eyedrop);
            }