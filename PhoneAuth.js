mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    @Override
    public void onVerificationCompleted(PhoneAuthCredential credential) {
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.
        Log.d(TAG, "onVerificationCompleted:" + credential);

        signInWithPhoneAuthCredential(credential);
    }

    @Override
    public void onVerificationFailed(FirebaseException e) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w(TAG, "onVerificationFailed", e);

        if (e instanceof FirebaseAuthInvalidCredentialsException) {
            // Invalid request
            // ...
        } else if (e instanceof FirebaseTooManyRequestsException) {
            // The SMS quota for the project has been exceeded
            // ...
        }

        // Show a message and update the UI
        // ...
    }

    @Override
    public void onCodeSent(@NonNull String verificationId,
                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        Log.d(TAG, "onCodeSent:" + verificationId);

        // Save verification ID and resending token so we can use them later
        mVerificationId = verificationId;
        mResendToken = token;

        // ...
    }
};
